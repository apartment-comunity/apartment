package com.apartment.apart.domain.communityReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommunityReplyService {
    private final CommunityReplyRepository communityReplyRepository;

    public CommunityReplyService(CommunityReplyRepository communityReplyRepository) {
        this.communityReplyRepository = communityReplyRepository;
    }

    public CommunityReply create(Community community, String content, SiteUser user) {
        CommunityReply communityReply = new CommunityReply(user, community, content);
        this.communityReplyRepository.save(communityReply);

        return communityReply;
    }

    public CommunityReply getCommunityReply(Integer id) {
        return this.communityReplyRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(CommunityReply communityReply, String content) {
        CommunityReply modifiedCommunityReply = communityReply.modify(content);
        this.communityReplyRepository.save(modifiedCommunityReply);
    }
    public void delete(CommunityReply communityReply) {
        this.communityReplyRepository.delete(communityReply);
    }

}
