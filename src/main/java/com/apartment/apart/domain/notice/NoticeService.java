package com.apartment.apart.domain.notice;

import com.apartment.apart.domain.user.SiteUser;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
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
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Page<Notice> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Notice> spec = search(kw);
        return this.noticeRepository.findAll(spec, pageable);
    }

    public Notice getNotice(Long id) {
        Optional<Notice> notice = this.noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new RuntimeException("error");
        }
    }

    public void create(@Valid NoticeForm noticeForm, SiteUser siteUser) {
        Notice notice = Notice.builder()
                .user(siteUser)
                .title(noticeForm.getTitle())
                .content(noticeForm.getContent())
                .createDate(LocalDateTime.now())
                .build();
        this.noticeRepository.save(notice);
    }

    public void modify(Notice notice, String title, String content) {
        Notice modiftNotice = Notice.builder()
                .id(notice.getId())
                .createDate(notice.getCreateDate())
                .modifiedDate(LocalDateTime.now())
                .title(title)
                .content(content)
                .user(notice.getUser())
                .build();
        this.noticeRepository.save(modiftNotice);
    }

    public void delete(Notice notice) {
        this.noticeRepository.delete(notice);
    }

    private Specification<Notice> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Notice> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Notice, SiteUser> u1 = q.join("user", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("nickname"), "%" + kw + "%"));    // 질문 작성자
            }
        };
    }
}