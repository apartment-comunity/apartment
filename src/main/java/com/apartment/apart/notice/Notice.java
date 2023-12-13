package com.apartment.apart.notice;

import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Notice")
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private SiteUser admin;
    @ManyToOne
    private SiteUser author;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;
}