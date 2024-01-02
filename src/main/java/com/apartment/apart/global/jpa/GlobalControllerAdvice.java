package com.apartment.apart.global.jpa;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UserService userService;

    @ModelAttribute
    public String globalAttributes(Model model, HttpServletRequest request, Principal principal) {
        // 이미 존재하는 경우에는 추가하지 않음
        if (model.containsAttribute("request")) {
            return "";
        }

        model.addAttribute("request", request);

        if (principal != null) {
            SiteUser loginUser = this.userService.getUser(principal.getName());

            if (loginUser != null) {
                if (!model.containsAttribute("loginUser")) {
                    model.addAttribute("loginUser", loginUser);
                } else {
                    // 이미 로그인한 사용자가 다시 로그인 페이지에 접근하는 경우 리다이렉트
                    return "redirect:/";
                }
            }
        }
        return "";
    }
}

