package com.apartment.apart.domain.voteTotal;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import com.apartment.apart.domain.vote.Vote;
import com.apartment.apart.domain.vote.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@ToString
public class VoteTotalController {

    private final VoteService voteService;
    private final UserService userService;
    private final VoteTotalService voteTotalService;



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{voteValue}/{id}")
    public String agree(Principal principal, @PathVariable("voteValue") String voteValue, @PathVariable("id") Long id) {
        Vote vote = this.voteService.findById(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        List<VoteTotal> voteList = vote.getVoteTotalList();


        Boolean isVoted = false;
        Boolean voteValueBoolean;

        for(VoteTotal voteTotal : voteList){
            if (voteTotal.getVoter().getUserId().equals(siteUser.getUserId())){
                isVoted = true;
                break;
            }
        }

        if(voteValue.equals("agree")){
            voteValueBoolean = true;
        } else if (voteValue.equals("disagree")) {
            voteValueBoolean = false;
        } else {
            return "redirect:/vote/list";
        }


        if (!isVoted) {
            this.voteTotalService.vote(siteUser,vote,voteValueBoolean);
        }

        return "redirect:/vote/list";

    }
}
