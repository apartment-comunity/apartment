package com.apartment.apart.domain.communityReply;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CommunityReply")
@Data
public class CommunityReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//댓글 아이디

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;//작성자 정보

    @ManyToOne//많은 댓글들이 게시글 하나에 작성된다
    @JoinColumn(name = "post_id")
    private Community community;//댓글 작성한 게시글 정보

    @Column(columnDefinition = "TEXT")
    private String content;//댓글 내용

    private int likeCount;//댓글 좋아요 수

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
    public CommunityReply(SiteUser user, Community community, String content) {
        this.user = user;
        this.community = community;
        this.content = content;
        this.likeCount = 0; // 좋아요 수 초기화
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }


}
