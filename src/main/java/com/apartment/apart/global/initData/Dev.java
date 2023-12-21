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
//            memberService.create("admin1", "관리자1", "admin", "01000000001","admin1@apart.com",001,001,true);
//            memberService.create("admin2", "관리자2", "admin", "01000000002","admin2@apart.com",002,002,true);
//            memberService.create("admin3", "관리자3", "admin", "01000000003","admin3@apart.com",003,003,true);
        };
    }
}