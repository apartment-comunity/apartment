package com.apartment.apart.domain.user;

import com.apartment.apart.domain.notice.Notice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
@Secured("ROLE_ADMIN")
public class AdminController {
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request, Principal principal, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<SiteUser> paging = this.userService.getList(page, kw);
        SiteUser loginUser = this.userService.getUser(principal.getName());

        if(loginUser.isCheckedAdmin()) {
            model.addAttribute("request", request);
            model.addAttribute("paging",paging);
            return "admin_page";
        }
        return "redirect:notice/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String userDelete(Principal principal, @PathVariable("id") String id) {
        SiteUser siteUser = this.userService.getUser(id);
        SiteUser loginUser = this.userService.getUser(principal.getName());
        if(loginUser.isCheckedAdmin()) {
            this.userService.delete(siteUser);
        }
        return "redirect:/admin/user/list";
    }
}
