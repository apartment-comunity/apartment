package com.apartment.apart.domain.community;

import com.apartment.apart.domain.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Community")
@Data
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //게시글 아이디

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user; //작성자 정보
    @Column(length = 50)
    private String title; //게시글 제목
    @Column(columnDefinition = "TEXT")
    private String content;//게시글 내용

    private int likeCount;//종아요 수

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}
