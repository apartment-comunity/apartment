package com.apartment.apart.domain.reportReply;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReportReplyForm {

    @NotEmpty(message = "답변 내용을 입력하세요.")
    private String replyContent;
}
