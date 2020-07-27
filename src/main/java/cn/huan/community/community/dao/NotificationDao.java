package cn.huan.community.community.dao;

import cn.huan.community.community.dto.NotificationDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationDao {

    List<NotificationDTO> listPageByUser(@Param("receiver")int receiver);
}
