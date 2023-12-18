package com.apartment.apart.domain.reportAnswer;

import com.apartment.apart.DataNotException;
import com.apartment.apart.domain.report.Report;
import com.apartment.apart.domain.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReportAnswerService {
    private final ReportAnswerRepository reportAnswerRepository;

    public ReportAnswer create(Report report, String content, SiteUser nickname) {
        ReportAnswer reportAnswer = ReportAnswer.builder()
                .content(content)
                .user(nickname).build();
        this.reportAnswerRepository.save(reportAnswer);

        return reportAnswer;
    }

    public ReportAnswer getReportAnswer(Integer id) {
        return this.reportAnswerRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(ReportAnswer reportAnswer, String content) {
        ReportAnswer reportAnswerModify = ReportAnswer.builder()
                .content(content).build();
        this.reportAnswerRepository.save(reportAnswerModify);
    }

    public void delete(ReportAnswer reportAnswer) {
        this.reportAnswerRepository.delete(reportAnswer);
    }

}
