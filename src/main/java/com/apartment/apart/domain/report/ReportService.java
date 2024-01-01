package com.apartment.apart.domain.report;

import com.apartment.apart.domain.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public Page<Report> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Report> spec = search(kw);
        return this.reportRepository.findAll(spec, pageable);
    }

    public Report getReport(Integer id, String username) {
        Optional<Report> report = this.reportRepository.findById(id);
        if (!report.isPresent()) {
            throw new RuntimeException("Report not found"); // 또는 적절한 예외 처리
        }

        Report r = report.get();
        if (r.isSecret() && !r.getUser().getUserId().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");
        }

        return r;
    }

    public void create(String title, String content, SiteUser user, boolean isSecret) {
        Report report = Report.builder()
                .title(title)
                .content(content)
                .user(user)
                .isSecret(isSecret)  // 비밀글 설정
                .build();
        this.reportRepository.save(report);
    }

    public void modify(Report Report, String title, String content) {
        Report modifiyReport = Report.toBuilder()
                .title(title)
                .content(content)
                .build();

        this.reportRepository.save(modifiyReport);
    }

    public void delete(Report report) {
        this.reportRepository.delete(report);
    }

    private Specification<Report> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Report> reportRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Report, SiteUser> userJoin = reportRoot.join("user", JoinType.LEFT);

                return cb.or(
                        cb.like(reportRoot.get("title"), "%" + kw + "%"), // 제목
                        cb.like(reportRoot.get("content"), "%" + kw + "%"), // 내용
                        cb.like(userJoin.get("nickname"), "%" + kw + "%") // 질문 작성자
                );
            }
        };
    }

    public void like(Report report, SiteUser siteUser) {
        report.getLikeCount().add(siteUser);
        this.reportRepository.save(report);
    }
}