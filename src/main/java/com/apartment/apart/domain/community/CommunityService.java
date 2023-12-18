package com.apartment.apart.domain.community;

import com.apartment.apart.domain.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    public Page<Community> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Community> spec = search(kw);
        return this.communityRepository.findAll(spec, pageable);
    }

    public Community getCommunity(Integer id) {
        Optional<Community> community = this.communityRepository.findById(id);
        if (community.isPresent()) {
            return community.get();
        } else {
            throw new RuntimeException("error");
        }
    }

    public void create(String title, String content, SiteUser nickname) {
        Community community = Community.builder()
                        .title(title)
                        .content(content)
                        .user(nickname).build();
        this.communityRepository.save(community);
    }

    public void modify(Community community, String title, String content) {
        Community modifyCommunity = Community.builder()
                .title(title)
                .content(content).build();
        this.communityRepository.save(modifyCommunity);
    }

    public void delete(Community community) {
        this.communityRepository.delete(community);
    }

    private Specification<Community> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Community> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Community, SiteUser> u1 = q.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("nickname"), "%" + kw + "%"));    // 질문 작성자
            }
        };
    }
}