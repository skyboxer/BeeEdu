package com.enablue.service;

import com.enablue.pojo.AppDetail;

import java.util.List;

/***
 * 拉取应用接口
 * wangcheng
 */
public interface PullApplicationService {
    List<AppDetail> getApplication(Integer applicationTypeId,Long serviceTotal);
}
