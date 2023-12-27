package com.apartment.apart.domain.community;

import com.apartment.apart.domain.communityReply.CommunityReplyForm;
import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Community> paging = this.communityService.getList(page, kw);
        model.addAttribute("paging", paging);
        return "community_list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, CommunityReplyForm communityReplyForm) {
        Community community = this.communityService.getCommunity(id);
        model.addAttribute("community", community);
        return "community_detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String communityCreate(CommunityForm communityForm) {
        return "community_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String communityCreate(@Valid CommunityForm communityForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "community_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.communityService.create(communityForm.getTitle(), communityForm.getContent(), siteUser);
        return "redirect:/community/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String communityModify(CommunityForm communityForm, @PathVariable("id") Long id, Principal principal) {
        Community community = this.communityService.getCommunity(id);
        if(!community.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        communityForm.setTitle(community.getTitle());
        communityForm.setContent(community.getContent());
        return "community_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String communityModify(@Valid CommunityForm communityForm, BindingResult bindingResult,
                                  Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "community_form";
        }
        Community community = this.communityService.getCommunity(id);
        if (!community.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.communityService.modify(community, communityForm.getTitle(), communityForm.getContent());
        return String.format("redirect:/community/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String communityDelete(Principal principal, @PathVariable("id") Long id) {
        Community community = this.communityService.getCommunity(id);
        if (!community.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.communityService.delete(community);
        return "redirect:/community/list";
    }

}