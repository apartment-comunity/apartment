package com.apartment.apart.domain.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public List<Vote> findAll() {
        return this.voteRepository.findAll();
    }
}
