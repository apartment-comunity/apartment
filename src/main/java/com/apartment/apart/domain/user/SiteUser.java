package com.apartment.apart.domain.user;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.communityReply.CommunityReply;
import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.reportReply.ReportReply;
import com.apartment.apart.domain.schedule.Schedule;
import com.apartment.apart.domain.vote.Vote;
import com.apartment.apart.domain.voteTotal.VoteTotal;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    private boolean checkedAdmin;

    @OneToMany
    private List<Community> communityList;

    @OneToMany
    private List<CommunityReply> communityReplyList;

    @OneToMany
    private List<Report> reportList;

    @OneToMany
    private List<ReportReply> reportReplyList;

    @OneToMany
    private List<VoteTotal> voteTotalList;

    @OneToMany
    private List<Schedule> scheduleList;
}