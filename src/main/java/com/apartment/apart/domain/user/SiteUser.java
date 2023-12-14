package com.apartment.apart.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SiteUser")
@Data
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
//    @Size(min = 10, max = 11, message = "전화번호 길이에 부합해야함.")
    private String phone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String apartment; // 동수입력

    @Column(unique = true)
    private String kakaoId;
    private boolean approval;
    private String profilePicture;
    private boolean emailVerified;
    private boolean welcomeEmailSent;
    private LocalDateTime createDate;
}