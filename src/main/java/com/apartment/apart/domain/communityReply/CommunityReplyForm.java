package com.apartment.apart.domain.communityReply;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class CommunityReplyForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
    public CommunityReplyForm(String content) {
        this.content = content;
    }


}
