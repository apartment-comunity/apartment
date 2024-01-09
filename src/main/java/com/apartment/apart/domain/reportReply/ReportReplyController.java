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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/reportReply")
@RequiredArgsConstructor
@Controller
public class ReportReplyController {
    private final ReportService reportService;
    private final ReportReplyService reportReplyService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") Long id, String userId,
                         @Valid ReportReplyForm reportReplyForm, BindingResult bindingResult, Principal principal) {

        //답변 부모 질문객체를 받아온다.
        Report report = this.reportService.getReport(id, userId);
        SiteUser user = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("report", report);
            return "report_detail";
        }

        ReportReply reportReply = this.reportReplyService.create(report, reportReplyForm.getReplyContent(), user);

        return "redirect:/report/detail/%d#reportReply_%s".formatted(id, reportReply.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String reportReplyModify(Model model, @PathVariable("id") Long id, Principal principal) {
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        if (!reportReply.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        ReportReplyForm reportReplyForm = new ReportReplyForm();
        reportReplyForm.setReplyContent(reportReply.getContent());

        model.addAttribute("reportReplyForm", reportReplyForm); // 수정 폼에 초기값으로 내용을 설정
        model.addAttribute("reportReplyId", id); // 수정 대상의 ID를 모델에 추가

        return "report_reply_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reportReplyModify(@Valid ReportReplyForm reportReplyForm, BindingResult bindingResult,
                                       @PathVariable("id") Long id, Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            return "reportReply_form";
        }

        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        if (!reportReply.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.reportReplyService.modify(reportReply, reportReplyForm.getReplyContent());
        return String.format("redirect:/report/detail/%s", reportReply.getReport().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String reportReplyDelete(Principal principal, @PathVariable("id") Long id) {
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        SiteUser user = this.userService.getUser(principal.getName());

        if (!reportReply.getUser().getUserId().equals(user.getUserId()) && !user.isCheckedAdmin()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.reportReplyService.delete(reportReply);
        return String.format("redirect:/report/detail/%s", reportReply.getReport().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/like/{id}")
    @ResponseBody
    public String reportReplyLike(Principal principal, @PathVariable("id") Long id) {
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.reportReplyService.like(reportReply, siteUser);

        ReportReply likedReportReply = this.reportReplyService.getReportReply(id);

        Integer count = likedReportReply.getLikeCount().size();
        return count.toString();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/unlike/{id}")
    @ResponseBody
    public String reportReplyUnlike(Principal principal, @PathVariable("id") Long id) {
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        try {
            this.reportReplyService.cancelLike(reportReply, siteUser);

            ReportReply unlikedReportReply = this.reportReplyService.getReportReply(id);
            Integer count = unlikedReportReply.getLikeCount().size();

            return count.toString();
        } catch (IllegalStateException e) {
            return "이미 추천을 취소한 상태이거나 추천을 하지 않은 경우입니다.";
        }
    }
}