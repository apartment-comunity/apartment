package com.apartment.apart.domain.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/list")
    public String list(Model model){
        List<Vote> voteList = voteService.findAll();
        Collections.reverse(voteList);

        LocalDate today = LocalDate.now();
        model.addAttribute("voteList",voteList);
        model.addAttribute("today",today);
        return "vote_list";
    }
}
