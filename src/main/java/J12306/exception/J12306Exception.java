package J12306.exception;

/**
 * 自定义异常类
 * Create by Kalvin on 2019/9/19.
 */
public class J12306Exception extends RuntimeException {
    private String msg;

    public J12306Exception(String message) {
        super(message);
        this.msg = message;
    }

    public J12306Exception(int errorCode, String message) {
        super(message);
        this.msg = message;
    }

    public J12306Exception(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }
}
