package com.apartment.apart.domain.vote;

import com.apartment.apart.domain.user.SiteUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public List<Vote> findAll() {
        return this.voteRepository.findAll();
    }

    public void save(@Valid VoteForm voteForm, SiteUser siteUser) {
        Vote vote = Vote.builder()
                .user(siteUser)
                .title(voteForm.getTitle())
                .content(voteForm.getContent())
                .startDate(LocalDate.parse(voteForm.getStart()))
                .endDate(LocalDate.parse(voteForm.getEnd()))
                .build();

        this.voteRepository.save(vote);
    }

    public Vote findById(Long id) {
        Optional<Vote> ov = this.voteRepository.findById(id);
        return ov.get();
    }

    public void delete(Vote vote) {
        this.voteRepository.delete(vote);
    }

    public Page<Vote> getPageList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.voteRepository.findAll(pageable);
    }

}
