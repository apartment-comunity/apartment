package com.apartment.apart.domain.schedule;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/schedule/list")
    public String test(Model model, @RequestParam(value = "targetDong", defaultValue = "100") int targetDong) {
        if (targetDong == 100) {
            List<Schedule> scheduleList = this.scheduleService.findAll();
            List<ScheduleForm> scheduleFormList = new ArrayList<>();
            for (Schedule schedule1 : scheduleList) {
                ScheduleForm sc1 = new ScheduleForm();
                sc1.setTitle(schedule1.getTitle());
                sc1.setStart(schedule1.getStartDate().toString());
                sc1.setEnd(schedule1.getEndDate().plusDays(1).toString());
                scheduleFormList.add(sc1);
            }
            model.addAttribute("scheduleList", scheduleFormList);

            return "schedule_list";
        } else {
            List<Schedule> scheduleList = this.scheduleService.findByTargetDong(targetDong);
            List<ScheduleForm> scheduleFormList = new ArrayList<>();
            for (Schedule schedule1 : scheduleList) {
                ScheduleForm sc1 = new ScheduleForm();
                sc1.setTitle(schedule1.getTitle());
                sc1.setStart(schedule1.getStartDate().toString());
                sc1.setEnd(schedule1.getEndDate().plusDays(1).toString());
                scheduleFormList.add(sc1);
            }
            model.addAttribute("scheduleList", scheduleFormList);
            return "schedule_list";
        }

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/schedule/create")
    public String create(Model model) {
        model.addAttribute("scheduleForm", new ScheduleForm());
        return "schedule_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/schedule/create")
    public String create(@Valid ScheduleForm scheduleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "schedule_form";
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        this.scheduleService.save(scheduleForm,siteUser);
        return "redirect:/schedule/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/schedule/mySchedule")
    public String mySchedule(Model model, Principal principal) {
        SiteUser siteUser = userService.getUser(principal.getName());
        List<Schedule> scheduleList = this.scheduleService.findByUser(siteUser);

        model.addAttribute("mySchedule", scheduleList);
        return "my_schedule";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/schedule/delete/{id}")
    public String delete(Principal principal, @PathVariable("id") Long id) {
        Schedule schedule = this.scheduleService.getSchedule(id);
        if (!schedule.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.scheduleService.delete(schedule);
        return "redirect:/schedule/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/schedule/modify/{id}")
    public String scheduleModify(@PathVariable Long id, Principal principal, ScheduleForm scheduleForm) {
        Schedule schedule = this.scheduleService.getSchedule(id);

        if (!schedule.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        scheduleForm.setTitle(schedule.getTitle());
        scheduleForm.setStart(String.valueOf(schedule.getStartDate()));
        scheduleForm.setEnd(String.valueOf(schedule.getEndDate()));

        return "schedule_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/schedule/modify/{id}")
    public String scheduleModify(@Valid ScheduleForm scheduleForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "schedule_form";
        }
        Schedule schedule = this.scheduleService.getSchedule(id);
        if (!schedule.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.scheduleService.modify(schedule, scheduleForm.getTitle(), scheduleForm.getStart(), scheduleForm.getEnd());
        return String.format("redirect:/schedule/list");
    }

}
