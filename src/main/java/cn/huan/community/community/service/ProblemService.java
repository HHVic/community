package cn.huan.community.community.service;

import cn.huan.community.community.dao.ProblemDao;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.PagenationDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.exception.CustomizeException;
import cn.huan.community.community.mapper.ProblemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private ProblemDao problemDao;

    @Transactional
    public void add(Problem problem) {
        problemMapper.insert(problem);
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
        pagenationDTO.setData(pageInfo.getList());
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
        pagenationDTO.setData(pageInfo.getList());
        return pagenationDTO;
    }



    public Problem getById(int id) {

        return problemMapper.selectByPrimaryKey(id);
    }

    public void createOrUpdate(Problem problem) {
        if(problem.getId() == null){
            //创建
            problem.setGmtCreate(System.currentTimeMillis());
            problem.setGmtModified(System.currentTimeMillis());
            problem.setCommentCount(0);
            problem.setLikeCount(0);
            problem.setViewCount(0);
            problemMapper.insert(problem);
        }else{
            //更新
            Problem problemsource = problemMapper.selectByPrimaryKey(problem.getId());
            problem.setCommentCount(problemsource.getCommentCount() == null ? 0 : problemsource.getCommentCount());
            problem.setLikeCount(problemsource.getLikeCount() == null ? 0 : problemsource.getLikeCount());
            problem.setViewCount(problemsource.getViewCount() == null ? 0 : problemsource.getViewCount());
            problem.setGmtCreate(problemsource.getGmtCreate());
            problem.setGmtModified(System.currentTimeMillis());
            problemMapper.updateByPrimaryKey(problem);
        }
    }

    public void incrView(Problem problem) {
        problemDao.incrView(problem.getId(),problem.getViewCount());
    }

    public void incrComment(Problem problem) {
        problemDao.incrComment(problem.getId(),problem.getCommentCount());
    }

    @Transactional
    public ProblemDTO getByIdWithUser(int id) {
        ProblemDTO problemDTO = problemDao.getByIdWithUser(id);
        validate(problemDTO);
        //增加访问量
        problemDao.incrView(problemDTO.getId(), problemDTO.getViewCount());
        return problemDTO;
    }

    public List<Problem> listByTagsRelations(ProblemDTO problemDTO) {
        validate(problemDTO);
        if(problemDTO.getTags() != null){
            String tags = problemDTO.getTags().replace(',','|');
            List<Problem> relations = problemDao.listByTagsRelations(problemDTO.getId(),tags);
            return relations;
        }
        return new ArrayList<>();
    }

    public void validate(ProblemDTO problemDTO){
        if(problemDTO == null){
            throw new CustomizeException(CustomizeErrorCode.PROBLEM_NOT_FOUND);
        }
        if(problemDTO.getAccount() == null){
            throw new CustomizeException(CustomizeErrorCode.ACCOUNT_NOT_FOUND);
        }
    }
}
