package cn.huan.community.community.service;

import cn.huan.community.community.dao.NotificationDao;
import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.Comment;
import cn.huan.community.community.domain.Notification;
import cn.huan.community.community.domain.NotificationExample;
import cn.huan.community.community.dto.NotificationDTO;
import cn.huan.community.community.dto.PagenationDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.enums.NotificationStatusEnum;
import cn.huan.community.community.enums.NotificationTypeEnum;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.exception.CustomizeException;
import cn.huan.community.community.mapper.NotificationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationDao notificationDao;

    //创建通知
    public void insert(Comment comment, Integer type, Integer receiver, String notifierName, String outerTitle, Integer outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(type);
        notification.setOuterId(outerId);
        notification.setReceiver(receiver);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public PagenationDTO<NotificationDTO> listPageByUser(int page, int size, Integer accountId) {
        PageHelper.startPage(page, size);
        PagenationDTO pagenationDTO = new PagenationDTO();
        PageInfo<NotificationDTO> pageInfo = new PageInfo<>(notificationDao.listPageByUser(accountId));
        List<NotificationDTO> data = pageInfo.getList();
        for(int i = 0;i < data.size();++i){
            data.get(i).setTypeName();
        }
        pagenationDTO.setPageNum(pageInfo.getPageNum());
        pagenationDTO.setPageSize(pageInfo.getPageSize());
        pagenationDTO.setTotalCount((int) pageInfo.getTotal());
        pagenationDTO.setTotalPage(pageInfo.getPages());
        pagenationDTO.setData(data);
        return pagenationDTO;
    }

    @Transactional
    public NotificationDTO read(Integer id, Account user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!Objects.equals(notification.getReceiver(), user.getId())){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_MATCHED);
        }
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName();
        //修改为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        return notificationDTO;
    }

    public long unread(Integer id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus())
                .andReceiverEqualTo(id);
        long count = notificationMapper.countByExample(notificationExample);
        return count;
    }
}
