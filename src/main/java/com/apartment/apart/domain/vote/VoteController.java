package com.apartment.apart.domain.vote;

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
    public String list(Model model) {
        List<Vote> voteList = voteService.findAll();
        Collections.reverse(voteList);

        LocalDate today = LocalDate.now();
        model.addAttribute("voteList", voteList);
        model.addAttribute("today", today);
        return "vote_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/create")
    public String create(Model model) {
        model.addAttribute("voteForm", new VoteForm());
        return "vote_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/vote/create")
    public String create(@Valid VoteForm voteForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "vote_form";
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        this.voteService.save(voteForm, siteUser);
        return "redirect:/vote/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/manage")
    public String myvote(Model model, Principal principal) {
        List<Vote> votelist = this.voteService.findAll();
        model.addAttribute("votelist", votelist);
        return "vote_manage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/delete/{id}")
    public String delete(Principal principal, @PathVariable("id") Long id) {
        Vote vote = this.voteService.getVote(id);
        if (!vote.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.voteService.delete(vote);
        return "redirect:/vote/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/modify/{id}")
    public String voteModify(@PathVariable Long id, Principal principal, VoteForm voteForm) {
        Vote vote = this.voteService.getVote(id);

        if (!vote.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        voteForm.setTitle(vote.getTitle());
        voteForm.setStart(String.valueOf(vote.getStartDate()));
        voteForm.setEnd(String.valueOf(vote.getEndDate()));

        return "vote_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/vote/modify/{id}")
    public String voteModify(@Valid VoteForm voteForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "vote_form";
        }
        Vote vote = this.voteService.getVote(id);
        if (!vote.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        this.voteService.modify(vote,voteForm,siteUser);
        return String.format("redirect:/vote/list");
    }
}
