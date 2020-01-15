package com.enablue.service;

import java.util.HashMap;

/**
 * 科大讯飞服务接口
 *   王成
 *  2019.12.05 15.44
 */
public interface IfasrService {
    HashMap<String, Object> speechTask(String fileName, String language);
    HashMap<String, Object> resultsQuery(String taskid, String language, String methods);
}
