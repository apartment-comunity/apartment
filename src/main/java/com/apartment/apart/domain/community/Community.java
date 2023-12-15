package com.apartment.apart.domain.community;

import com.apartment.apart.domain.communityReply.CommunityReply;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Community extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user; //작성자 정보
    @Column(length = 50)
    private String title; //게시글 제목
    @Column(columnDefinition = "TEXT")
    private String content;//게시글 내용

    Set<SiteUser> likeCount;//종아요 수

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<CommunityReply> answers = new ArrayList<>();
}
