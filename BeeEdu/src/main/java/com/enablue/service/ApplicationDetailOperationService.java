package com.enablue.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/***
 * 应用详情操作日志模块
 * 王成
 */
public interface ApplicationDetailOperationService {
    HashMap<String, Object> queryAllApplicationDetailOperation(Long page, Long limit);
}
