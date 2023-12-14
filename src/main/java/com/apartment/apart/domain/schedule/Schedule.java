package com.apartment.apart.domain.schedule;

import com.apartment.apart.domain.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Schedule")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    private String content;

    private String targetDong;

    private LocalDate startDate;

    private LocalDate endDate;
}
