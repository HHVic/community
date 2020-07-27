package cn.huan.community.community.dto;

import cn.huan.community.community.domain.Notification;
import cn.huan.community.community.enums.NotificationTypeEnum;
import lombok.Data;

public class NotificationDTO extends Notification {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName() {
        this.typeName = NotificationTypeEnum.nameOf(super.getType());
    }
}
