package cn.huan.community.community.service;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.AccountExample;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public int loginFromGithub(GithubUser user, String token) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountIdEqualTo(user.getId().toString());
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        Account u = null;
        if (!CollectionUtils.isEmpty(accounts)) {
            u = accounts.get(0);
        }
        if (u == null) {
            u = new Account();
            u.setAccountId(user.getId().toString());
            u.setGmtCreate(System.currentTimeMillis());
            u.setUserName(user.getLogin());
            u.setGmtModified(System.currentTimeMillis());
            u.setAvatarUrl(user.getAvatarUrl());
            u.setToken(token);
            log.info("插入用户:{}", u);
            //插入
            return accountMapper.insert(u);
        } else {
            //更新token
            log.info("更新用户token:{}", token);
            u.setToken(token);
            u.setGmtModified(System.currentTimeMillis());
            return accountMapper.updateByPrimaryKey(u);
        }
    }

    public Account getByToken(String token) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andTokenEqualTo(token);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        if (accounts != null && accounts.size() > 0) {
            return accounts.get(0);
        }
        return null;
    }
}