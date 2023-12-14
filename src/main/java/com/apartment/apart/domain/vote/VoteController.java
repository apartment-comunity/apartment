package com.apartment.apart.domain.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VoteController {

    @GetMapping("/vote/list")
    public String list(){

        return "vote_list";
    }
}
