package com.apartment.apart.domain.schedule;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Schedule extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    private String content;

    private int targetDong;

    private LocalDate startDate;

    private LocalDate endDate;
}
