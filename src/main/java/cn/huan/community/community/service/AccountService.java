package cn.huan.community.community.service;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.AccountExample;
import cn.huan.community.community.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public int loginFromGithub(Account Account){
        Account u = accountMapper.selectByPrimaryKey(Account.getId());
        if(u == null){
            //插入
            return accountMapper.insert(Account);
        }else{
            //更新token
            u.setAvatarUrl(Account.getAvatarUrl());
            u.setGmtModified(Account.getGmtModified());
            u.setToken(Account.getToken());
            u.setUserName(Account.getUserName());
            return accountMapper.updateByPrimaryKey(u);
        }
    }

    public Account getByToken(String token) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andTokenEqualTo(token);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        if(accounts != null && accounts.size() > 0){
            return accounts.get(0);
        }
        return null;
    }
}