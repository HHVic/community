package cn.huan.community.community.enums;

public enum NotificationTypeEnum {
    REPLY_PROBLEM(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }


    public String getName() {
        return name;
    }

    public static String nameOf(int type){
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if(value.getType() == type)
                return value.getName();
        }
        return "";
    }


    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
