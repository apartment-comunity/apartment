package com.apartment.apart.domain.reportReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ReportReplyService {
    private final ReportReplyRepository reportReplyRepository;

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

    public void cancelLike(ReportReply reportReply, SiteUser siteUser) {
        if (reportReply.getLikeCount().contains(siteUser)) {
            reportReply.getLikeCount().remove(siteUser);
            this.reportReplyRepository.save(reportReply);
        } else {
            throw new IllegalStateException("이미 추천을 취소한 상태이거나, 추천을 하지 않은 경우입니다.");
        }
    }
}
