package com.enablue.service;

import com.enablue.pojo.ApplicationDetail;

import java.util.List;

/***
 * 拉取应用接口
 * wangcheng
 */
public interface PullApplicationService {
    List<ApplicationDetail> getApplication(Long applicationTypeId,Long serviceTotal);
}
