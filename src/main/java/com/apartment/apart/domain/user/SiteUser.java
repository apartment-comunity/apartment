package com.apartment.apart.domain.user;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.communityReply.CommunityReply;
import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.reportReply.ReportReply;
import com.apartment.apart.domain.schedule.Schedule;
import com.apartment.apart.domain.voteTotal.VoteTotal;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class SiteUser extends BaseEntity {
    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String nickname;

    @Setter
    private String password;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private int apartDong;

    private int apartHo;

    private boolean checkedAdmin;

    private boolean checkedWithdrawal;

    public void setCheckedWithdrawal(boolean checkedWithdrawal) {
        this.checkedWithdrawal = checkedWithdrawal;
    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<Community> communityList;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<CommunityReply> communityReplyList;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Report> reportList;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<ReportReply> reportReplyList;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Schedule> scheduleList;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<VoteTotal> voteTotalList;

}