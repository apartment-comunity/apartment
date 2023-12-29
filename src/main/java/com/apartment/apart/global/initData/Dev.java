package com.apartment.apart.global.initData;

import com.apartment.apart.domain.notice.NoticeService;
import com.apartment.apart.domain.user.SiteUser;
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
    public ApplicationRunner init(UserService userService, NoticeService noticeService) {
        return args -> {
//            userService.create("admin1", "관리자1", "admin", "01000000001","admin1@apart.com",100,001,true);
//            userService.create("admin2", "관리자2", "admin", "01000000002","admin2@apart.com",100,002,true);
//            userService.create("user1", "유저1","123123","01011111111","user1@apart.com",101,101,false);
//            userService.create("user2", "유저2","123123","01022222222","user2@apart.com",102,102,false);
        };
    }
}