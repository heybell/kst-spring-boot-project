package com.bell.kst.constants;

public enum StatusCode {
    SIGNIN_SUCCESS(1000, "접근 성공"),
    SIGNIN_FAIL(2000, "로그인 실패을 실패하였습니다."),
    SIGNUP_FAIL(3000, "회원가입에 실패하였습니다.");

    private final Integer code;
    private final String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
