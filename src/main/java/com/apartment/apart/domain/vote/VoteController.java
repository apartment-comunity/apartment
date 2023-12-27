package com.apartment.apart.domain.vote;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/list")
    public String list(Model model,
                       Principal principal,
                       @RequestParam(value = "type", defaultValue = "card") String type,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw,
                       @RequestParam(value = "status", defaultValue = "total") String status,
                       HttpServletRequest request) {
        if (type.equals("list")) {
            Page<Vote> paging = this.voteService.getPageList(page, kw,status);




            String nowStatus = switch (status) {
                case "total" -> "전체";
                case "inProgress" -> "진행중";
                case "closed" -> "지난 투표";
                case "intended" -> "투표 예정";
                default -> "";
            };
            model.addAttribute("paging", paging);
            model.addAttribute("type", type);
            model.addAttribute("request", request);
            model.addAttribute("kw", kw);
            model.addAttribute("status", nowStatus);

            return "vote/vote_list_list";
        } else if (type.equals("card")) {
            List<Vote> voteList = voteService.findAll();
            Collections.reverse(voteList);
            LocalDate today = LocalDate.now();
            SiteUser loginUser = this.userService.getUser(principal.getName());

            model.addAttribute("voteList", voteList);
            model.addAttribute("today", today);
            model.addAttribute("loginUser", loginUser);
            model.addAttribute("type", type);
            model.addAttribute("request", request);

            return "vote/vote_list";
        } else {
            return "redirect:/";
        }

    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/vote/list")
    public String typeSelectList(Model model,
                                 Principal principal,
                                 @RequestParam("type") String type,
                                 HttpServletRequest request) {
        if (type.equals("list")) {
            return "vote/vote_list";
        } else if (type.equals("card")) {
            List<Vote> voteList = voteService.findAll();
            Collections.reverse(voteList);

            LocalDate today = LocalDate.now();

            SiteUser loginUser = this.userService.getUser(principal.getName());

            model.addAttribute("voteList", voteList);
            model.addAttribute("today", today);
            model.addAttribute("loginUser", loginUser);
            model.addAttribute("request", request);
            return "vote/vote_list";
        } else {
            return "redirect:/";
        }
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/create")
    public String create(Model model,
                         HttpServletRequest request) {
        model.addAttribute("voteForm", new VoteForm());
        model.addAttribute("request", request);
        return "vote/vote_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/vote/create")
    public String create(@Valid VoteForm voteForm, BindingResult bindingResult, Principal principal,
                         HttpServletRequest request, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("request", request);
            return "vote/vote_form";
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        this.voteService.save(voteForm, siteUser);
        return "redirect:/vote/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/manage")
    public String manage(Model model, Principal principal,
                         HttpServletRequest request) {
        List<Vote> votelist = this.voteService.findAll();
        model.addAttribute("votelist", votelist);
        model.addAttribute("request", request);
        return "vote/vote_manage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/delete/{id}")
    public String delete(Principal principal, @PathVariable("id") Long id) {
        Vote vote = this.voteService.findById(id);
        if (!vote.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.voteService.delete(vote);
        return "redirect:/vote/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id,
                         HttpServletRequest request,
                         Principal principal) {

        LocalDate today = LocalDate.now();
        SiteUser loginUser = this.userService.getUser(principal.getName());
        model.addAttribute("vote", this.voteService.findById(id));
        model.addAttribute("request", request);
        model.addAttribute("today", today);
        model.addAttribute("loginUser", loginUser);
        return "vote/vote_detail";
    }

}
