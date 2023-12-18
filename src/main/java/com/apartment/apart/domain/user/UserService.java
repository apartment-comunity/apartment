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
        SiteUser user = new SiteUser();
//        user.setUserId(username);
//        user.setNickname(nickname);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setPhone(phone);
//        user.setEmail(email);
//        user.setApartDong(apartDong);
//        user.setApartHo(apartHo);
        user.setCreateDate(LocalDateTime.now());
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
}

