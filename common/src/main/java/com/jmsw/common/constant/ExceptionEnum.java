package com.jmsw.common.constant;

public enum ExceptionEnum {
    SysException("100", "系统异常", "网络开小差了，请稍候再试！"),
    LockException("300", "分布式锁异常", "网络开小差了，请稍候再试！"),
    BusiException("500", "业务逻辑异常", "网络开小差了，请稍候再试！"),
    Exception("1000", "未知错误", "网络开小差了，请稍后再试"),
    NEEDTOKEN("1001", "用户未登录或者被挤掉了", "请重新登录"),
    TOKEN_EXPIRE("1002", "短期token过期", "网络开小差了，请稍候再试！"),
    TOKEN_INVALID("1003", "无效token", "请重新登录");

    private String code;

    private String showmsg;

    private String errorMsg;

    /*ExceptionEnum(String code, String msg, String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }*/

    ExceptionEnum(String code, String errorMsg, String showmsg) {
        this.code = code;
        this.showmsg = showmsg;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getShowMsg() {
        return showmsg;
    }

/*    public static final Map<String, ExceptionEnum> exceptionEnumMap = new HashMap<>();

    static {
        if (ExceptionEnum.values() != null && ExceptionEnum.values().length > 0) {
            for (ExceptionEnum e : ExceptionEnum.values()) {
                exceptionEnumMap.put(e.getCode(), e);
            }
        }
    }*/

}
