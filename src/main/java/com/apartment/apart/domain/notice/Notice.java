package com.apartment.apart.domain.notice;


import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
public class Notice extends BaseEntity {
    @ManyToOne
    private SiteUser user;

    private String title;

    private String content;
}