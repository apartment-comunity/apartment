package com.apartment.apart.community;

import com.apartment.apart.communityReply.CommunityReply;
import com.apartment.apart.communityReply.CommunityReplyForm;
import com.apartment.apart.communityReply.CommunityReplyService;
import com.apartment.apart.user.SiteUser;
import com.apartment.apart.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;

import java.security.Principal;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommunityController {

    private final CommunityService communityService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Community> paging = this.communityService.getList(page);
        model.addAttribute("paging", paging);

        return "community_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommunityReplyForm communityReplyForm) {
        Community community = this.communityService.getCommunity(id);

        model.addAttribute("community", community);

        return "community_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("communityForm", new CommunityForm("제목", "내용"));
        return "community_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String communityCreate(@Valid CommunityForm communityForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "community_form";
        }

        SiteUser siteUser = this.userService.getUser(principal.getName());

        Community community = this.communityService.create(communityForm.getTitle(), communityForm.getContent(), siteUser);

        return "redirect:/community/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String communityModify(@PathVariable Integer id, Principal principal, CommunityForm communityForm) {
        Community community = this.communityService.getCommunity(id);

        if (!community.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        communityForm = new CommunityForm(community.getContent(), community.getTitle());

        return "community_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String communityModify(@Valid CommunityForm communityForm, BindingResult bindingResult,
                                  Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "community_form";
        }
        Community community = this.communityService.getCommunity(id);
        if (!community.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.communityService.modify(community, communityForm.getTitle(), communityForm.getContent());
        return String.format("redirect:/community/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String communityDelete(Principal principal, @PathVariable("id") Integer id) {
        Community community = this.communityService.getCommunity(id);
        if (!community.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.communityService.delete(community);
        return "redirect:/";
    }
}

