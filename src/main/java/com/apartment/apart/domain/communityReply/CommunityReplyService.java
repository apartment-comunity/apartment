package com.apartment.apart.domain.communityReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.user.SiteUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CommunityReplyService {
    private final CommunityReplyRepository communityReplyRepository;

    public CommunityReplyService(CommunityReplyRepository communityReplyRepository) {
        this.communityReplyRepository = communityReplyRepository;
    }
    @Transactional
    public CommunityReply create(Community community, String content, SiteUser user) {

        CommunityReply createCommunityReply = CommunityReply.builder()
                .content(content)
                        .community(community)
                                .user(user)
                                        .build();
        this.communityReplyRepository.save(createCommunityReply);
        return createCommunityReply;
    }

    public CommunityReply getCommunityReply(Integer id) {
        return this.communityReplyRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(CommunityReply communityReply, String content) {
        CommunityReply modifyCommunityReply = communityReply.toBuilder()
                .content(content)
                .build();
        this.communityReplyRepository.save(modifyCommunityReply);
    }
    public void delete(CommunityReply communityReply) {
        this.communityReplyRepository.delete(communityReply);
    }

}
