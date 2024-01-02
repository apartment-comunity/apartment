package com.apartment.apart.domain.user;

import com.apartment.apart.domain.notice.Notice;
import com.apartment.apart.global.jpa.BaseEntity;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String nickname, String password, String phone, String email, int apartDong, int apartHo, boolean checkedAdmin) {
        SiteUser user = SiteUser.builder()
                .userId(username)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .email(email)
                .apartDong(apartDong)
                .apartHo(apartHo)
                .checkedAdmin(checkedAdmin)
                .build();
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
        return create(username, nickname, "", "", "", 0, 0,false); // 최초 로그인 시 딱 한번 실행
    }

    private Optional<SiteUser> findByUserId(String username) {
        return userRepository.findByUserId(username);
    }

    public List<SiteUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void modify(SiteUser siteUser, String nickname, String password, String phone,
                       String email, int apartDong, int apartHo) {
        Optional<SiteUser> updateUser = this.userRepository.findByUserId(siteUser.getUserId());
        SiteUser modifyUser = SiteUser.builder()
                .id(updateUser.get().getId())
                .userId(siteUser.getUserId())
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .email(email)
                .apartDong(apartDong)
                .apartHo(apartHo)
                .createDate(siteUser.getCreateDate())
                .checkedAdmin(siteUser.isCheckedAdmin())
                .build();
        this.userRepository.save(modifyUser);
    }

    public Page<SiteUser> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<SiteUser> spec = search(kw);
        return this.userRepository.findAll(spec, pageable);
    }

    private Specification<SiteUser> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<SiteUser> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                return cb.like(q.get("nickname"), "%" + kw + "%");
            }
        };
    }

    public void delete(SiteUser siteUser) {
        this.userRepository.delete(siteUser);
    }
}