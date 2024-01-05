package com.apartment.apart.domain.reportReply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportReplyRepository extends JpaRepository<ReportReply, Long> {
}
