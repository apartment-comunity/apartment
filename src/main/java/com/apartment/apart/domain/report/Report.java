package com.apartment.apart.domain.report;

import com.apartment.apart.domain.communityReply.CommunityReply;
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

    private boolean isSecret;  // 비밀글 여부 필드 추가
}

