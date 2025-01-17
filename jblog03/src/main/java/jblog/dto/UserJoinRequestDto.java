package jblog.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserJoinRequestDto {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z가-힣 ]{1,45}$", message = "알파벳 대소문자와 한글, 공백만 사용할 수 있습니다.")
    private String name;
    /***
     User ID & Blog ID
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{1,50}$", message = "알파벳 대소문자와 숫자만 사용할 수 있습니다.")
    private String id;

    @NotEmpty
    @Pattern(regexp = "^[ -~]{1,16}$", message = "알파벳 대소문자와 숫자, 특수문자만 사용할 수 있습니다.")
    private String password;

    @NotNull
    @AssertTrue(message = "약관 동의가 필요합니다.")
    private boolean agreedToTerm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAgreedToTerm() {
        return agreedToTerm;
    }

    public void setAgreedToTerm(boolean agreedToTerm) {
        this.agreedToTerm = agreedToTerm;
    }
}
