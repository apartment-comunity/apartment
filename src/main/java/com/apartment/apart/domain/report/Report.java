package com.apartment.apart.domain.report;

import com.apartment.apart.domain.reportAnswer.ReportAnswer;
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
public class Report extends BaseEntity {
    @ManyToOne
    private SiteUser user; //작성자 정보
    @Column(length = 50)
    private String title; //게시글 제목
    @Column(columnDefinition = "TEXT")
    private String content;//게시글 내용

    Set<SiteUser> likeCount;//종아요한 유저

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<ReportReply> replyList = new ArrayList<>();

    private boolean isSecret;  // 비밀글 여부 필드 추가
}
