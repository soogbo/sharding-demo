package xyz.sharding.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;

/**
 * 统一异常处理
 * @author shisp
 * @date 2018-7-20 10:10:04
 */
@RestControllerAdvice
public class FrameExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(FrameExceptionHandler.class);

    @ExceptionHandler(value = Exception.class) // 表示可处理Exception以内异常
    public GeneralResponse<String> handleGolbalException(HttpServletRequest request, Exception exception) {
        String paramStr = JSON.toJSONString(request.getParameterMap());

        logger.error("请求失败：request={},params={}", request.getRequestURI(), paramStr, exception);

        if (exception instanceof FrameServiceException) {
            FrameServiceException frameException = (FrameServiceException) exception;

            return GeneralResponse.success(frameException.getErrorCode(), frameException.getErrorMsg());
        } else {
            return GeneralResponse.success(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage());
        }
    }

}
