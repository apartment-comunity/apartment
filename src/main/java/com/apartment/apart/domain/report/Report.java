package com.apartment.apart.domain.report;

import com.apartment.apart.domain.reportReply.ReportReply;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {
    @ManyToOne
    private SiteUser user;

    private String title;

    private String content;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<ReportReply> answers = new ArrayList<>();

    @ManyToMany
    Set<SiteUser> likeCount;
}
