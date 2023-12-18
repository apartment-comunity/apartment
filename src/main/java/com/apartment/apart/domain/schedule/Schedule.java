package com.apartment.apart.domain.schedule;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends BaseEntity {
    @JoinColumn(name = "user_id")
    private SiteUser user;

    private String content;

    private int targetDong;

    private LocalDate startDate;

    private LocalDate endDate;
}
