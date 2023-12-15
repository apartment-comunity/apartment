package com.apartment.apart.domain.vote;


import com.apartment.apart.domain.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Vote")
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Set<SiteUser> voter;

    private String title;

    private String content;

    private int vote;

    private LocalDateTime createDate;

    private LocalDate startDate;

    private LocalDate endDate;
}