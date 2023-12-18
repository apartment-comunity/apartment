package com.apartment.apart.domain.communityReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CommunityReplyService {

    private final CommunityReplyRepository communityReplyRepository;


    public CommunityReply create(Community community, String content, SiteUser nickname) {
        CommunityReply communityReply = CommunityReply.builder()
                .user(nickname)
                .community(community)
                .content(content)
                .build();
        this.communityReplyRepository.save(communityReply);

        return communityReply;
    }

    public CommunityReply getCommunityReply(Integer id) {
        return this.communityReplyRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(CommunityReply communityReply, String content) {
        CommunityReply modifiedCommunityReply = CommunityReply.builder()
                .id(communityReply.getId())
                .community(communityReply.getCommunity())
                .user(communityReply.getUser())
                .content(content)
                .createDate(communityReply.getCreateDate())
                .modifiedDate(LocalDateTime.now())
                .likeCount(communityReply.getLikeCount())
                .build();
        this.communityReplyRepository.save(modifiedCommunityReply);
    }

    public void delete(CommunityReply communityReply) {
        this.communityReplyRepository.delete(communityReply);
    }

}
