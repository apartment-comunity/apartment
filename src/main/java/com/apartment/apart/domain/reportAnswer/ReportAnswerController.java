package com.apartment.apart.domain.reportAnswer;

import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.report.ReportService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/reportAnswer")
@RequiredArgsConstructor
@Controller
public class ReportAnswerController {
    private final ReportService reportService;
    private final ReportAnswerService reportAnswerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createReportAnswer(Model model, @PathVariable("id") Integer id,
                                       @Valid ReportAnswerForm reportAnswerForm, BindingResult bindingResult, Principal principal) {

        // 답변 부모 질문 객체를 받아온다.
        Report report = this.reportService.getReport(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("report", report);
            return "report_detail";
        }

        ReportAnswer reportAnswer = this.reportAnswerService.create(report, reportAnswerForm.getContent(), siteUser);

        return "redirect:/report/detail/%d".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String reportAnswerModify(ReportAnswerForm reportAnswerForm, @PathVariable("id") Integer id, Principal principal) {
        ReportAnswer reportAnswer = this.reportAnswerService.getReportAnswer(id);
        if (!reportAnswer.getUser().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        reportAnswerForm = new ReportAnswerForm(reportAnswer.getContent());

        return "report_reply_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reportAnswerModify(@Valid ReportAnswerForm reportAnswerForm, BindingResult bindingResult,
                                       @PathVariable("id") Integer id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "report_reply_form";
        }
        ReportAnswer reportAnswer = this.reportAnswerService.getReportAnswer(id);
        if (!reportAnswer.getUser().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.reportAnswerService.modify(reportAnswer, reportAnswerForm.getContent());
        return String.format("redirect:/report/detail/%s", reportAnswer.getReport().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String reportAnswerDelete(Principal principal, @PathVariable("id") Integer id) {
        ReportAnswer reportAnswer = this.reportAnswerService.getReportAnswer(id);
        if (!reportAnswer.getUser().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.reportAnswerService.delete(reportAnswer);
        return String.format("redirect:/report/detail/%s", reportAnswer.getReport().getId());
    }
}