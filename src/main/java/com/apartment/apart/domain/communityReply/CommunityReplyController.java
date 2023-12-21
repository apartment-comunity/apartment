package com.apartment.apart.domain.communityReply;

import com.apartment.apart.domain.community.Community;
import com.apartment.apart.domain.community.CommunityService;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/communityReply")
@RequiredArgsConstructor
@Controller
public class CommunityReplyController {
    private final CommunityService communityService;
    private final CommunityReplyService communityReplyService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createCommunityReply(Model model, @PathVariable("id") Integer id,
                                       @Valid CommunityReplyForm communityReplyForm, BindingResult bindingResult, Principal principal) {

        Community community = this.communityService.getCommunity(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("community", community);
            model.addAttribute("communityReplyForm", communityReplyForm); // 에러 발생 시 폼 데이터를 다시 모델에 추가
            return "community_detail";
        }

        CommunityReply communityReply = this.communityReplyService.create(community, communityReplyForm.getContent(), siteUser);

        return "redirect:/community/detail/%d".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String communityReplyModify(Model model, @PathVariable("id") Integer id, Principal principal) {
        CommunityReply communityReply = this.communityReplyService.getCommunityReply(id);
        if (!communityReply.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        model.addAttribute("communityReplyForm", new CommunityReplyForm(communityReply.getContent())); // 수정 폼에 초기값으로 내용을 설정
        model.addAttribute("communityReplyId", id); // 수정 대상의 ID를 모델에 추가

        return "communityReply_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String communityReplyModify(@Valid CommunityReplyForm communityReplyForm, BindingResult bindingResult,
                                       @PathVariable("id") Integer id, Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            return "communityReply_form";
        }

        CommunityReply communityReply = this.communityReplyService.getCommunityReply(id);
        if (!communityReply.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.communityReplyService.modify(communityReply, communityReplyForm.getContent());
        return String.format("redirect:/community/detail/%s", communityReply.getCommunity().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String communityReplyDelete(Principal principal, @PathVariable("id") Integer id) {
        CommunityReply communityReply = this.communityReplyService.getCommunityReply(id);
        if (!communityReply.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.communityReplyService.delete(communityReply);
        return String.format("redirect:/community/detail/%s", communityReply.getCommunity().getId());
    }
}