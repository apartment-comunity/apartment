package com.apartment.apart.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        Optional<SiteUser> siteUser = this.userRepository.findByuserId(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new RuntimeException("siteuser not found");
        }
    }
}

