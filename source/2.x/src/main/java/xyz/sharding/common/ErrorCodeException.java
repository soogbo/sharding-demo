package xyz.sharding.common;

import java.io.Serializable;

/**
 * 错误码异常类型，所有自定义的异常的根类
 *
 */
public class ErrorCodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 系统ID
     */
    private String systemId;

    /**
     * 扩展对象
     */
    private Serializable data;

    public ErrorCodeException(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorCodeException(String errorCode, String errorMsg, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorCodeException(String errorCode, String errorMsg, Serializable data) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ErrorCodeException(String errorCode, String errorMsg, Serializable data, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ErrorCodeException(String errorCode, String errorMsg, String systemId, Serializable data) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.systemId = systemId;
        this.data = data;
    }

    public ErrorCodeException(String errorCode, String errorMsg, String systemId, Serializable data, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.systemId = systemId;
        this.data = data;
    }


    public String getErrorCode() {
        return errorCode;
    }


    public String getErrorMsg() {
        return errorMsg;
    }


    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Object getData() {
        return data;
    }


    @Override
    public String getMessage() {
        String superMessage = super.getMessage();
        StringBuilder result = new StringBuilder();
        if (superMessage != null && superMessage.trim() != "") {
            result.append("message=[").append(superMessage).append("],");
        }
        result.append("errorCode='").append(errorCode).append("\'")
                .append(", errorMsg='").append(errorMsg).append("\'")
                .append(", systemId='").append(systemId).append("\'")
                .append(", data='").append(data).append("\'");
        return result.toString();
    }
}
