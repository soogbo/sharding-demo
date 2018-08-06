package xyz.sharding.common;


import xyz.sharding.common.ErrorCodeException;
import xyz.sharding.common.ResultCodeEnum;


/**
 * @author shisp
 * @date 2018-7-20 09:54:30
 */
public class FrameServiceException extends ErrorCodeException {
    private static final long serialVersionUID = 1L;

    public FrameServiceException(ResultCodeEnum resultCode) {
        super(resultCode.getCode(), resultCode.getMessage());
    }

	public FrameServiceException(String code, String message) {
        super(code, message);
    }
}
