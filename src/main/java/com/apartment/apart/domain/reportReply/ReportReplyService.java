package com.apartment.apart.domain.reportReply;

import com.apartment.apart.DataNotException;

import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ReportReplyService {
    private final ReportReplyRepository reportReplyRepository;

    public ReportReplyService(ReportReplyRepository reportReplyRepository) {
        this.reportReplyRepository = reportReplyRepository;
    }
    @Transactional
    public ReportReply create(Report report, String content, SiteUser user) {

        ReportReply createReportReply = ReportReply.builder()
                .content(content)
                .report(report)
                .user(user)
                .build();
        this.reportReplyRepository.save(createReportReply);
        return createReportReply;
    }

    public ReportReply getReportReply(Long id) {
        return this.reportReplyRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(ReportReply reportReply, String content) {
        ReportReply modifyReportReply = reportReply.toBuilder()
                .content(content)
                .build();
        this.reportReplyRepository.save(modifyReportReply);
    }
    public void delete(ReportReply reportReply) {
        this.reportReplyRepository.delete(reportReply);
    }
    public void like(ReportReply reportReply, SiteUser siteUser) {
        reportReply.getLikeCount().add(siteUser);
        this.reportReplyRepository.save(reportReply);
    }
}
