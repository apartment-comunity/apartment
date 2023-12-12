package com.apartment.apart.schedule;

import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
    private String category;
    private String content;
    private String targetDong;
    private LocalDateTime startDate; // createdAt을 createDate로 변경
    private LocalDateTime endDate; // createdAt을 createDate로 변경
    private LocalDateTime createDate; // createdAt을 createDate로 변경
}
