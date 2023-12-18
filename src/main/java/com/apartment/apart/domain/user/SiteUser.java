package com.apartment.apart.domain.user;

import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
public class SiteUser extends BaseEntity {
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private int apartDong; // 동수입력

    private int apartHo; //호수입력

    private boolean approval;

    private boolean adminCheck;
}