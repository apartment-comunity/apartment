package com.apartment.apart.domain.user;

import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SiteUser extends BaseEntity {

    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
//    @Size(min = 10, max = 11, message = "전화번호 길이에 부합해야함.")
    private String phone;

    @Column(unique = true)
    private String email;

    private int apartDong; // 동수입력

    private int apartHo; //호수입력

    private boolean approval;

    private boolean adminCheck;
}