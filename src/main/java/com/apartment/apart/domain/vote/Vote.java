package com.apartment.apart.domain.vote;


import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Vote")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends BaseEntity {
    @ManyToOne
    private SiteUser user;

    private String title;

    private String content;

    @ManyToMany
    Set<SiteUser> agree;

    @ManyToMany
    Set<SiteUser> disagree;

    private LocalDate startDate;

    private LocalDate endDate;
}