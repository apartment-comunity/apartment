package com.apartment.apart.domain.vote;


import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Vote")
@Data
public class Vote extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    private String title;

    private String content;

    Set<SiteUser> agree;

    Set<SiteUser> disagree;

    private LocalDate startDate;

    private LocalDate endDate;
}