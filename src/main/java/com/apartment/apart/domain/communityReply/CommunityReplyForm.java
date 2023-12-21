package com.apartment.apart.domain.communityReply;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityReplyForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;

    // 다른 생성자는 필요 없을 것 같아서 생략
}
