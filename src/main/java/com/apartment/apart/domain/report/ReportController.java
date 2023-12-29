package com.apartment.apart.domain.report;

import com.apartment.apart.domain.reportReply.ReportReplyForm;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Report> paging = this.reportService.getList(page, kw);
        model.addAttribute("paging", paging);
        return "report_list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, ReportReplyForm reportReplyForm) {
        Report report = this.reportService.getReport(id);
        model.addAttribute("report", report);
        return "report_detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String reportCreate(ReportForm reportForm) {
        return "report_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String reportCreate(@Valid ReportForm reportForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "report_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.reportService.create(reportForm.getTitle(), reportForm.getContent(), siteUser);
        return "redirect:/report/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String reportModify(ReportForm reportForm, @PathVariable("id") Long id, Principal principal) {
        Report report = this.reportService.getReport(id);
        if(!report.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        reportForm.setTitle(report.getTitle());
        reportForm.setContent(report.getContent());
        return "report_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reportModify(@Valid ReportForm reportForm, BindingResult bindingResult,
                                  Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "report_form";
        }
        Report report = this.reportService.getReport(id);
        if (!report.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.reportService.modify(report, reportForm.getTitle(), reportForm.getContent());
        return String.format("redirect:/report/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String reportDelete(Principal principal, @PathVariable("id") Long id) {
        Report report = this.reportService.getReport(id);
        if (!report.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.reportService.delete(report);
        return "redirect:/report/list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/like/{id}")
    @ResponseBody
    public String reportLike(Principal principal, @PathVariable("id") Long id) {
        Report report = this.reportService.getReport(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.reportService.like(report, siteUser);

        Report likedReport = this.reportService.getReport(id);
        Integer count = likedReport.getLikeCount().size();

        return count.toString();
    }
}