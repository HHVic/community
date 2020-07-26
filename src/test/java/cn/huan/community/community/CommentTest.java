package cn.huan.community.community;


import cn.huan.community.community.dao.CommentDao;
import cn.huan.community.community.dto.CommentDTO;
import cn.huan.community.community.mapper.AccountMapper;
import cn.huan.community.community.mapper.ProblemMapper;
import cn.huan.community.community.service.ProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes={CommentDao.class,CommunityApplication.class, AccountMapper.class, ProblemMapper.class, ProblemService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentTest {
    @Autowired
    private CommentDao commentDao;
    @Test
    public void listByParentId(){
        List<CommentDTO> commentDTOS = commentDao.listByParentId(16,2);
        System.out.println(commentDTOS);
    }
}
