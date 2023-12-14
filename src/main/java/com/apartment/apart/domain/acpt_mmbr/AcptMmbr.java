package com.apartment.apart.acpt_mmbr;

import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "AcptMmbr")
@Data
public class AcptMmbr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;
    private LocalDateTime createDate; // createdAt을 createDate로 변경
}
