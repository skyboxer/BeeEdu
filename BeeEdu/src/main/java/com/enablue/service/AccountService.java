package com.enablue.service;

import com.enablue.pojo.Account;

import java.util.HashMap;
import java.util.List;

/**
 * 用户服务接口
 * 王成
 * 2019.12.3 11:08
 */

public interface AccountService {
     HashMap<String, Object> login(Account account);

    HashMap<String, Object> queryAllAccount(Long page, Long limit);

    HashMap<String, Object> addAccount(Account account);

    HashMap<String, Object> deleteAccount(Long id);

    HashMap<String, Object> updataAccount(Account account);
}
