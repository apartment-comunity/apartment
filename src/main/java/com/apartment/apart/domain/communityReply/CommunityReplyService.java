package com.apartment.apart.communityReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.community.Community;
import com.apartment.apart.user.SiteUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommunityReplyService {
    private final CommunityReplyRepository communityReplyRepository;
    private CommunityReplyService communityReplyService;

    public CommunityReply create( SiteUser user,Community post, String content) {
        return this.communityReplyRepository.save(new CommunityReply(user,post,content));
    }

    public CommunityReply getCommunityReply(Integer id) {
        Optional<CommunityReply> communityReply = this.communityReplyRepository.findById(id);
        if (communityReply.isPresent()) {
            return communityReply.get();
        } else {
            throw new DataNotException("community reply not found");
        }
    }

    public String communityReplyModify(@Valid CommunityReplyForm communityReplyForm, BindingResult bindingResult,
                                       @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "community_reply_form";
        }

        CommunityReply communityReply = this.communityReplyService.getCommunityReply(id);

        if (!communityReply.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.communityReplyService.modify(communityReply, communityReplyForm.getContent());

        return String.format("redirect:/community/detail/%s", communityReply.getPost().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String communityReplyDelete(Principal principal, @PathVariable("id") Long id) {
        CommunityReply communityReply = this.communityReplyService.getCommunityReply(id);

        if (!communityReply.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.communityReplyService.delete(communityReply);

        return String.format("redirect:/community/detail/%s", communityReply.getPost().getId());
    }
}
