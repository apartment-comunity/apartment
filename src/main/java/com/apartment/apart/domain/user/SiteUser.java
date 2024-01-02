package com.apartment.apart.domain.user;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.communityReply.CommunityReply;
import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.schedule.Schedule;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class SiteUser extends BaseEntity {
    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private int apartDong;

    private int apartHo;

    private boolean approval;

    private boolean checkedAdmin;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Report> reportList;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    private List<ReportReply> reportReplyList;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Schedule> scheduleList;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    private List<VoteTotal> voteTotalList;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Community> communityList;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<CommunityReply> communityReplyList;
}