package cn.huan.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    //1XXXX问题异常
    PROBLEM_NOT_FOUND(1001,"该文章不存在或者已被删除"),
    //2XXXX用户异常
    CUSTOM_NOT_FOUND(2001,"用户未登录"),
    ACCOUNT_NOT_FOUND(2002,"用户不存在"),
    //3XXXX评论异常
    TARGET_PARENT_NOT_FOUND(3001,"未选择任何评论"),
    TYPE_PARAM_WRONG(3002,"评论类型不存在"),
    COMMENT_NOT_FOUND(3003,"评论不存在"),
    COMMENT_COMTENT_EMPTY(3004,"评论内容为空"),
    //4XXXX通知异常
    NOTIFICATION_NOT_FOUND(4001,"通知不存在"),
    NOTIFICATION_NOT_MATCHED(4002,"此通知不属于你"),
    //5XXXX系统异常
    SYSTEM_ERROR(5001,"服务器异常"),

    ;

    private Integer code;
    private String message;
    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CustomizeErrorCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
