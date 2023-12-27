package com.apartment.apart.domain.reportReply;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReportReplyService {
//    private final ReportReplyRepository reportReplyRepository;
//
//    public ReportReply create(Report report, String content, SiteUser nickname) {
//        ReportReply reportReply = ReportReply.builder()
//                .content(content)
//                .user(nickname).build();
//        this.reportReplyRepository.save(reportReply);
//
//        return reportReply;
//    }
//
//    public ReportReply getReportAnswer(Long id) {
//        return this.reportReplyRepository.findById(id)
//                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
//    }
//
//    public void modify(ReportReply reportReply, String content) {
//        ReportReply reportReplyModify = ReportReply.builder()
//                .content(content).build();
//        this.reportReplyRepository.save(reportReplyModify);
//    }
//
//    public void delete(ReportReply reportReply) {
//        this.reportReplyRepository.delete(reportReply);
//    }

}
