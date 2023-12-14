package com.apartment.apart.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateForm {

    @NotEmpty(message = "사용자ID는 필수 입력 항목입니다.")
    @Size(min = 6, max = 15, message = "사용자ID는 6자 이상 15자 이하로 입력해주세요.")
    private String username;

    @NotEmpty(message = "닉네임은 필수 입력 항목입니다.")
    @Size(min = 3, max = 15, message = "닉네임은 3자 이상 15자 이하로 입력해주세요.")
    private String nickname;

    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String Password1;

    @NotEmpty(message = "비밀번호 재확인은 필수입니다.")
    private String Password2;

    @NotEmpty(message = "전화번호는 필수 입력 항목입니다.")
    @Size(min = 10, max = 11, message = "전화번호는 - 기호를 빼고 10자리에서 11자리로 입력해주세요.")
    private String phone;

    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @Pattern(regexp = "\\d+동\\d+호", message = "올바른 형식의 아파트 정보를 입력하세요. 띄어쓰기 X (예: 101동101호)")
    @NotEmpty(message = "아파트 정보는 비어있을 수 없습니다.")
//    @Size(min = 5, max = 15, message = "올바른 형식의 아파트 정보를 입력하세요 (예: 101동101호)")
    private String apartment;

    private String kakaoId;

    private boolean approval;

    private String profilePicture;

    private boolean emailVerified;

    private boolean welcomeEmailSent;
}