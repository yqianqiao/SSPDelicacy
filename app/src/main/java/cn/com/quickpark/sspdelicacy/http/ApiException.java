package cn.com.quickpark.sspdelicacy.http;

/**
 * Created by y on 2018/7/11.
 */

public class ApiException extends RuntimeException {
    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;
    private static String message;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }


    public ApiException(String detailMessage) {
        super(detailMessage);
    }


    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case -5182:
                message = "验证码错误";
                break;
            case 500:
                message = "服务器繁忙！";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }


    /**
     * 直接返回信息
     *
     * @param msg
     */
    public ApiException(int resultCode, String msg) {
        message = msg;
    }

}
