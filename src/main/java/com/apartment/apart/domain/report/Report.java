package com.apartment.apart.domain.report;

import com.apartment.apart.domain.reportAnswer.ReportAnswer;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Report extends BaseEntity {

    @ManyToOne
    private SiteUser author;

    private String title;

    private String content;

    @OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE)
    private List<ReportAnswer> answers;

    @ManyToMany
    Set<SiteUser> likeCount;
}
