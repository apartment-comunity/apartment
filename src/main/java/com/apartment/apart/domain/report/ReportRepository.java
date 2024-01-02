package com.apartment.apart.domain.report;

import com.apartment.apart.domain.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    Page<Report> findAll(Pageable pageable);
    Page<Report> findAll(Specification<Report> spec, Pageable pageable);

    Optional<SiteUser> findByUserId(String userId);
}

