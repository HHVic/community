package cn.huan.community.community.service;

import cn.huan.community.community.dao.ProblemDao;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.PagenationDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.mapper.ProblemMapper;
import cn.huan.community.community.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PagenationDTO<ProblemDTO> listPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        PagenationDTO pagenationDTO = new PagenationDTO();
        PageInfo<ProblemDTO> pageInfo = new PageInfo<>(list());

        pagenationDTO.setPageNum(pageInfo.getPageNum());
        pagenationDTO.setPageSize(pageInfo.getPageSize());
        pagenationDTO.setTotalCount((int) pageInfo.getTotal());
        pagenationDTO.setTotalPage(pageInfo.getPages());
        pagenationDTO.setProblems(pageInfo.getList());
        return pagenationDTO;
    }

    public PagenationDTO<ProblemDTO> listPageByUser(Integer page, Integer size,int userId) {
        PageHelper.startPage(page, size);
        PagenationDTO pagenationDTO = new PagenationDTO();
        PageInfo<ProblemDTO> pageInfo = new PageInfo<>(problemDao.listByUser(userId));

        pagenationDTO.setPageNum(pageInfo.getPageNum());
        pagenationDTO.setPageSize(pageInfo.getPageSize());
        pagenationDTO.setTotalCount((int) pageInfo.getTotal());
        pagenationDTO.setTotalPage(pageInfo.getPages());
        pagenationDTO.setProblems(pageInfo.getList());
        return pagenationDTO;
    }
}
