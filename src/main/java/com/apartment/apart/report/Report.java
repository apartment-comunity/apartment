package com.apartment.apart.report;

import com.apartment.apart.notice.Notice;
import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Report")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;
    private String content;
    private String adminReply;
    private LocalDateTime createDate; // createdAt을 createDate로 변경
}
