package com.enablue.service;

import com.enablue.pojo.AppDetail;

import java.util.List;

/**
 * @author chinaxjk
 * 应用详情
 */
public interface AppDetailService {


    List<AppDetail> getAppDetailList();

    int addAppDetail(AppDetail appDetail);

    int updateAppDetail(AppDetail appDetail);
}
