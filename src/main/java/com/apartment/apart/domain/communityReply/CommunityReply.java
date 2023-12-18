package com.apartment.apart.domain.communityReply;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityReply extends BaseEntity {
    @JoinColumn(name = "user_id")
    private SiteUser user;//작성자 정보

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Community community;

    @Column(columnDefinition = "TEXT")
    private String content;//댓글 내용

    Set<SiteUser> likeCount;//댓글 좋아요 수

}
