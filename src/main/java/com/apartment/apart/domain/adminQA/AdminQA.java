package com.apartment.apart.domain.adminQA;


import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "AdminQA")
@Data
public class AdminQA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qaId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;
    private String content;
    private boolean isSecret;
    private LocalDateTime createDate; // createdAt을 createDate로 변경
}
