package com.apartment.apart.domain.reportAnswer;

import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
public class ReportAnswer extends BaseEntity {
    @JoinColumn(name = "user_id")
    private SiteUser user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Report report;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToMany
    Set<SiteUser> likeCount;
}
