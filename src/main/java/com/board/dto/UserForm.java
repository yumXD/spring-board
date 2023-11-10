package com.board.dto;

import com.board.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserForm {
    private Long id;

    @Size(min = 3, max = 25, message = "이름은 3 ~ 25 자 이어야합니다.")
    @NotEmpty(message = "이름은 필수항목입니다.")
    private String username;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    public UserForm(User user) {
        this.id = user.getId();
        this.username = user.getActualUsername();
        this.email = user.getEmail();
    }
}
