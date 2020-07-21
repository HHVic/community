package cn.huan.community.community.service;

import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.mapper.ProblemMapper;
import cn.huan.community.community.mapper.UserMapper;
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

    @Transactional
    public void add(Problem problem) {
        problemMapper.add(problem);
    }

    public List<ProblemDTO> list() {
        List<ProblemDTO> list = new ArrayList<>();
        List<Problem> problem = problemMapper.list();
        for (Problem pro : problem) {
            ProblemDTO problemDTO = new ProblemDTO();
            BeanUtils.copyProperties(pro,problemDTO);
            problemDTO.setUser(userMapper.getById(pro.getCreator()));
            list.add(problemDTO);
        }
        return list;
    }
}
