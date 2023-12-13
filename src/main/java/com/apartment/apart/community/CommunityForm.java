package com.apartment.apart.community;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
@Getter
@ToString
public class CommunityForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200)
    private final String title;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private final String content;

    public CommunityForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}