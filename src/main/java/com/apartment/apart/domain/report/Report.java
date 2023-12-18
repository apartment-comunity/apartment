package com.apartment.apart.domain.report;

import com.apartment.apart.domain.reportAnswer.ReportAnswer;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Report extends BaseEntity {
    private SiteUser user;

    private String title;

    private String content;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<ReportAnswer> answers = new ArrayList<>();

    @ManyToMany
    Set<SiteUser> likeCount;
}
