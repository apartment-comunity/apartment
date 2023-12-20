package com.apartment.apart.global.initData;

import com.apartment.apart.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class Dev {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner init(UserService memberService) {
        return args -> {
            memberService.create("admin", "관리자", "admin", "01099999999","admin@apart.com",110,1501);
        };
    }
}