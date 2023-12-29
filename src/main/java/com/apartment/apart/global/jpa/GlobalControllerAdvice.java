package com.apartment.apart.global.jpa;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void globalAttributes(Model model, HttpServletRequest request) {
        // 이미 존재하는 경우에는 추가하지 않음
        if (!model.containsAttribute("request")) {
            model.addAttribute("request", request);
        }

    }
}