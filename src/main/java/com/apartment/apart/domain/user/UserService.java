package com.apartment.apart.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String nickname, String password, String phone, String email, int apartDong,int apartHo) {
        SiteUser user = SiteUser.builder()
                .userId(username)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .email(email)
                .apartDong(apartDong)
                .apartHo(apartHo).build();
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUserId(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new RuntimeException("siteuser not found");
        }
    }

    @Transactional
    public SiteUser whenSocialLogin(String providerTypeCode, String username, String nickname) {
        Optional<SiteUser> opUser = userRepository.findByUserId(username);

        if (opUser.isPresent()) return opUser.get();
        // 소셜 로그인를 통한 가입시 비번은 없다.
        return create(username, nickname, "", "", "", "",""); // 최초 로그인 시 딱 한번 실행
    }

    private Optional<SiteUser> findByUsername(String username) {
        return userRepository.findByUserId(username);
    }
}