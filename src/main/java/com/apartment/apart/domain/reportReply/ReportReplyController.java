package com.apartment.apart.domain.reportReply;

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
public class ReportReplyController {
//    private final ReportService reportService;
//    private final ReportReplyService reportReplyService;
//    private final UserService userService;
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create/{id}")
//    public String createReportAnswer(Model model, @PathVariable("id") Long id,
//                                     @Valid ReportReplyForm reportReplyForm, BindingResult bindingResult, Principal principal) {
//
//        Report report = this.reportService.getReport(id);
//        SiteUser siteUser = this.userService.getUser(principal.getName());
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("report", report);
//            return "report_detail";
//        }
//
//        ReportReply reportReply = this.reportReplyService.create(report, reportReplyForm.getContent(), siteUser);
//
//        return "redirect:/report/detail/%d".formatted(id);
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/modify/{id}")
//    public String reportAnswerModify(ReportReplyForm reportReplyForm, @PathVariable("id") Long id, Principal principal) {
//        ReportReply reportReply = this.reportReplyService.getReportAnswer(id);
//        if (!reportReply.getUser().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
//        reportReplyForm = new ReportReplyForm(reportReply.getContent());
//
//        return "report_reply_form";
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/modify/{id}")
//    public String reportAnswerModify(@Valid ReportReplyForm reportReplyForm, BindingResult bindingResult,
//                                     @PathVariable("id") Long id, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            return "report_reply_form";
//        }
//        ReportReply reportReply = this.reportReplyService.getReportAnswer(id);
//        if (!reportReply.getUser().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
//        this.reportReplyService.modify(reportReply, reportReplyForm.getContent());
//        return String.format("redirect:/report/detail/%s", reportReply.getReport().getId());
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/delete/{id}")
//    public String reportAnswerDelete(Principal principal, @PathVariable("id") Long id) {
//        ReportReply reportReply = this.reportReplyService.getReportAnswer(id);
//        if (!reportReply.getUser().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//        }
//        this.reportReplyService.delete(reportReply);
//        return String.format("redirect:/report/detail/%s", reportReply.getReport().getId());
//    }
}