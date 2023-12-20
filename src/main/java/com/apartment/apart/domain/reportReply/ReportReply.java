package com.apartment.apart.domain.reportReply;

import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@SuperBuilder
public class ReportReply extends BaseEntity {
    @ManyToOne
    private SiteUser user;

    @ManyToOne
    private Report report;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToMany
    Set<SiteUser> likeCount;
}
