package com.apartment.apart.communityReply;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CommunityReplyForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
    public CommunityReplyForm(String content) {
        this.content = content;
    }


}
