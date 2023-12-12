package com.apartment.apart.vote;

import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Vote")
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;
    private String title;
    private String option1;
    private String option2;
    private LocalDateTime createDate; // createdAt을 createDate로 변경
    private LocalDateTime closedAt; // createdAt을 createDate로 변경
}