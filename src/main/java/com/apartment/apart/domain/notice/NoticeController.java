package com.apartment.apart.domain.notice;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String list(Model model, Principal principal,@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw, HttpServletRequest request) {
        Page<Notice> paging = this.noticeService.getList(page, kw);
        SiteUser loginUser = this.userService.getUser(principal.getName());
        model.addAttribute("paging", paging);
        model.addAttribute("loginUser",loginUser);
        return "notice/notice_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, HttpServletRequest request,
                         Principal principal) {
        Notice notice = this.noticeService.getNotice(id);
        SiteUser loginUser = this.userService.getUser(principal.getName());
        model.addAttribute("notice", notice);
        model.addAttribute("loginUser", loginUser);
        return "notice/notice_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(Model model,
                         HttpServletRequest request,
                         Principal principal) {

        SiteUser loginUser = this.userService.getUser(principal.getName());

        if(loginUser.isCheckedAdmin()) {
            model.addAttribute("noticeForm", new NoticeForm());
            model.addAttribute("request", request);
            return "notice/notice_form";
        }
        return "redirect:notice/list";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal,
                         HttpServletRequest request, Model model) {

        SiteUser loginUser = userService.getUser(principal.getName());
        if (loginUser.isCheckedAdmin()) {
            if (bindingResult.hasErrors()) {

                model.addAttribute("request", request);
                return "notice/notice_form";
            }

            this.noticeService.create(noticeForm, loginUser);
            return "redirect:/notice/list";
        }
        return "redirect:/notice/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String noticeModify(NoticeForm noticeForm, @PathVariable("id") Long id, Principal principal) {
        Notice notice = this.noticeService.getNotice(id);
        if(!notice.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        noticeForm.setTitle(notice.getTitle());
        noticeForm.setContent(notice.getContent());
        return "notice/notice_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String noticeModify(@Valid NoticeForm noticeForm, BindingResult bindingResult,
                               Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "notice/notice_form";
        }
        Notice notice = this.noticeService.getNotice(id);
        if(!notice.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.noticeService.modify(notice, noticeForm.getTitle(), noticeForm.getContent());
        return String.format("redirect:/notice/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String noticeDelete(Principal principal, @PathVariable("id") Long id) {
        Notice notice = this.noticeService.getNotice(id);
        if(!notice.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.noticeService.delete(notice);
        return "redirect:/";
    }
}