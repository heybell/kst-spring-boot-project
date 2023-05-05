package com.bell.kst.constants;

public enum StatusCode {
    SUCCESS(200, "처리가 성공적으로 완료되었습니다. 반환된 결과를 확인하세요."),
    ACCEPTED(202, "처리는 완료되었지만, 반환된 결과가 원하는 것과 다릅니다."),
    CONFLICT(409, "요청이 충돌로 인해 처리되지 못했습니다."),
    NOT_FOUND(404, "요청한 자원을 찾을 수 없습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다. 요청한 자원에 유효하지 않은 값이 포함되어 있을 수 있습니다."),
    INTERNAL_SERVER_ERROR(500, "서버에서 오류가 발생했습니다. 문제가 지속되면 관리자에게 문의해주세요.");

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
