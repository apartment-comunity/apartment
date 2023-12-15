package com.apartment.apart.domain.reportAnswer;

import com.apartment.apart.domain.communityReply.CommunityReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportAnswerRepository extends JpaRepository<ReportAnswer, Integer> {
}
