package service;

import java.util.HashMap;

/**
 * 用户服务接口
 * 王成
 * 2019.12.3 11:08
 */
public interface AccountService {
     HashMap<String, Object> login(String name, String password);
}
