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
        ReportAnswer reportAnswer = new ReportAnswer();
        reportAnswer.setContent(content);
        reportAnswer.setUser(nickname);
        this.reportAnswerRepository.save(reportAnswer);

        return reportAnswer;
    }

    public ReportAnswer getReportAnswer(Integer id) {
        return this.reportAnswerRepository.findById(id)
                .orElseThrow(() -> new DataNotException("답변을 찾을 수 없습니다."));
    }

    public void modify(ReportAnswer reportAnswer, String content) {
        reportAnswer.setContent(content);
        this.reportAnswerRepository.save(reportAnswer);
    }

    public void delete(ReportAnswer reportAnswer) {
        this.reportAnswerRepository.delete(reportAnswer);
    }

}
