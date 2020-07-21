package cn.huan.community.community.service;

import cn.huan.community.community.dao.ProblemDao;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.PageProblemDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.mapper.ProblemMapper;
import cn.huan.community.community.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProblemDao problemDao;

    @Transactional
    public void add(Problem problem) {
        problemMapper.add(problem);
    }

    public List<ProblemDTO> list() {
        return problemDao.list();
    }

    public PageProblemDTO listPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        PageProblemDTO pageProblemDTO = new PageProblemDTO();
        PageInfo<ProblemDTO> pageInfo = new PageInfo<>(list());

        pageProblemDTO.setPageNum(pageInfo.getPageNum());
        pageProblemDTO.setPageSize(pageInfo.getPageSize());
        pageProblemDTO.setTotalCount((int) pageInfo.getTotal());
        pageProblemDTO.setTotalPage(pageInfo.getPages());
        pageProblemDTO.setProblems(pageInfo.getList());
        return pageProblemDTO;
    }
}
