package com.apartment.apart.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/test")
    public String test(Model model) {
        List<Schedule> scheduleList = this.scheduleService.findall();
        List<ScheduleForm> scheduleFormList = new ArrayList<>();
        for (Schedule schedule1 : scheduleList){
            ScheduleForm sc1 = new ScheduleForm();
            sc1.setTitle(schedule1.getContent());
            sc1.setStart(schedule1.getStartDate().toString());
            sc1.setEnd(schedule1.getEndDate().plusDays(1).toString());
            scheduleFormList.add(sc1);
        }
        String today = LocalDate.now().toString();
        model.addAttribute("scheduleList",scheduleFormList);
        model.addAttribute("today",today);

        return "test";
    }

}
