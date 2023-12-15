package com.apartment.apart.domain.communityReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.user.SiteUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CommunityReplyService {
    private final CommunityReplyRepository communityReplyRepository;

    public CommunityReplyService(CommunityReplyRepository communityReplyRepository) {
        this.communityReplyRepository = communityReplyRepository;
    }

    public CommunityReply create(Community community, String content, SiteUser nickname) {
        // 새로운 CommunityReply 객체를 생성하고 필요한 값들을 설정합니다.
        CommunityReply communityReply = new CommunityReply();
        communityReply.setContent(content);
        communityReply.setCommunity(community);
        communityReply.setUser(nickname); // 예시로 nickname을 setUser로 설정하였습니다.

        // 데이터베이스에 저장합니다.
        communityReplyRepository.save(communityReply);

        // 저장된 객체를 반환합니다.
        return communityReply;
    }

    public CommunityReply getCommunityReply(Integer id) {
        return this.communityReplyRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(CommunityReply communityReply, String content) {
        // 기존 CommunityReply 객체의 내용을 수정합니다.
        communityReply.setContent(content);

        // 수정된 객체를 저장합니다.
        this.communityReplyRepository.save(communityReply);
    }
    public void delete(CommunityReply communityReply) {
        this.communityReplyRepository.delete(communityReply);
    }

}
