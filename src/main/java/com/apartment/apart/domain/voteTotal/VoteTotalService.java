package com.apartment.apart.domain.voteTotal;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.vote.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteTotalService {

    private final VoteTotalRepository voteTotalRepository;
    public void vote(SiteUser siteUser, Vote vote, Boolean voteValueBoolean) {
        VoteTotal voteTotal = VoteTotal.builder()
                .voter(siteUser)
                .vote(vote)
                .agree(voteValueBoolean)
                .build();

        this.voteTotalRepository.save(voteTotal);
    }
}
