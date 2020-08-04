package cn.huan.community.community.service;

import cn.huan.community.community.domain.Tag;
import cn.huan.community.community.domain.TagExample;
import cn.huan.community.community.dto.TagDTO;
import cn.huan.community.community.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public List<Tag> getFromMysql() {
        return tagMapper.selectByExample(new TagExample());
    }
}
