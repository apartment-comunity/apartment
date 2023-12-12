package com.apartment.apart.community;

import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Community")
@Data
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;
    private String title;
    private String content;
    private int likes;
    private LocalDateTime createDate; // createdAt을 createDate로 변경
}
