package com.apartment.apart.domain.reportReply;

import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
