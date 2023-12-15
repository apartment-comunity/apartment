package com.apartment.apart.domain.user;

import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Data
public class SiteUser extends BaseEntity {

    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
    @Size(min = 10, max = 11, message = "전화번호 길이에 부합해야함.")
    private String phone;

    @Column(unique = true)
    private String email;

    private int apartDong; // 동수입력

    private int apartHo; //호수입력

    private boolean approval;

    private boolean adminCheck;
}