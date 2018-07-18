package xyz.sharding.common;

/**
 * 返回结果码
 * @author shisp
 * @date 2018-7-18 18:24:45
 */
public enum ResultCodeEnum {

    /* 通用 */
    SUCCESS("0", "成功"),
    LOGIN_SUCCESS("0", "登录成功"),
    LOGIN_FAILD("101", "登录失败"),
    SYSUSER_NOT_EXIST("102", "用户不存在"),
    SYSUSER_INVALID("103", "用户被禁用"),
    LOGIN_USER_ERROR("104", "登录用户获取错误"),

    /* 4000 ~ 4200 外部请求异常 */
    PARAM_ERROR("4000", "参数异常"),
    PARAM_IS_NULL("4001", "参数为空"),
    SIGN_ERROR("4016", "签名错误"),
    FILE_UPLOAD_ERROR("4017", "文件上传错误"),
    FILE_DOWNLOAD_ERROR("4018", "文件下载错误"),
    FILE_NOT_EXIST("4021", "文件不存在"),
    FILE_GENERATE_EXIST("4023", "生成文件错误"),
    UNAUTHENTICATED_ERROR("4024","未登陆"),

    /* 4201 ~ 4500 内部服务 */
    TIME_CONVERT_ERROR("4201", "时间转换异常"),


    /* 系统异常 */
    SYSTEM_ERROR("5000", "系统异常"),
    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
