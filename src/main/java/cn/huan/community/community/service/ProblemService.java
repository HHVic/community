package cn.huan.community.community.service;

import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;
    @Transactional
    public void add(Problem problem) {
        problemMapper.add(problem);
    }
}
