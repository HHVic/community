package cn.huan.community.community.enums;

import java.util.Objects;

public enum CommentTypeEnum {

    PROBLEM(1),
    COMMENT(2)
    ;
    private Integer type;
    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if(Objects.equals(value.getType(),type)){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
