package cn.huan.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    PROBLEM_NOT_FOUND("该文章不存在或者已被删除");

    private String message;
    @Override
    public String getMessage(){
        return message;
    }

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
