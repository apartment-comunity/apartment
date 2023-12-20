package com.apartment.apart.global.security;

import com.apartment.apart.domain.user.SiteUser;
import com.apartment.apart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> _user = this.userRepository.findByUserId(username);

        if (_user.isEmpty()) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }

        SiteUser siteUser = _user.get();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        return new User(siteUser.getUserId(), siteUser.getPassword(), authorityList);
    }

}
