package com.apartment.apart.domain.communityReply;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommunityReply extends BaseEntity {
    @ManyToOne
    private SiteUser user;//작성자 정보

    @ManyToOne
    private Community community;

    @Column(columnDefinition = "TEXT")
    private String content;//댓글 내용

    @ManyToMany
    Set<SiteUser> likeCount;//댓글 좋아요 수
}
