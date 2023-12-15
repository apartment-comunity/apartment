package com.apartment.apart;

import com.apartment.apart.domain.vote.Vote;
import com.apartment.apart.domain.vote.VoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class ApartApplicationTests {
    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void test1(){
        Vote vote = new Vote();
        vote.setTitle("101동 청소업체 찬반투표");
        vote.setContent("101동 청소 찬반투표입니다. 1000만원내고 청소업체 부르기 찬/반 입니다");
        vote.setCreateDate(LocalDateTime.now());
        vote.setStartDate(LocalDate.parse("2023-12-12"));
        vote.setEndDate(LocalDate.parse("2023-12-16"));

        Vote vote2 = new Vote();
        vote2.setTitle("102동 청소업체 찬반투표");
        vote2.setContent("101동 청소 찬반투표입니다. 1000만원내고 청소업체 부르기 찬/반 입니다");
        vote2.setCreateDate(LocalDateTime.now());
        vote2.setStartDate(LocalDate.parse("2023-12-12"));
        vote2.setEndDate(LocalDate.parse("2023-12-16"));

        Vote vote3 = new Vote();
        vote3.setTitle("103동 청소업체 찬반투표");
        vote3.setContent("103동 청소 찬반투표입니다. 1000만원내고 청소업체 부르기 찬/반 입니다");
        vote3.setCreateDate(LocalDateTime.now());
        vote3.setStartDate(LocalDate.parse("2023-12-12"));
        vote3.setEndDate(LocalDate.parse("2023-12-16"));

        Vote vote4 = new Vote();
        vote4.setTitle("104동 청소업체 찬반투표");
        vote4.setContent("104동 청소 찬반투표입니다. 1000만원내고 청소업체 부르기 찬/반 입니다");
        vote4.setCreateDate(LocalDateTime.now());
        vote4.setStartDate(LocalDate.parse("2023-12-16"));
        vote4.setEndDate(LocalDate.parse("2023-12-19"));


        voteRepository.save(vote);
        voteRepository.save(vote2);
        voteRepository.save(vote3);
        voteRepository.save(vote4);
    }

}


