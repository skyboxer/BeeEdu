package com.enablue.util.tencent;

import com.tencentcloudapi.common.Credential;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;

// 导入对应产品模块的client

import com.tencentcloudapi.cvm.v20170312.CvmClient;

// 导入要请求接口对应的request response类

import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesRequest;

import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesResponse;

import com.tencentcloudapi.cvm.v20170312.models.Filter;

//导入可选配置类

import com.tencentcloudapi.common.profile.ClientProfile;

import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.DriverLicenseOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.DriverLicenseOCRResponse;


/**
 * @author cn_xjk
 * tencent腾讯请求
 */
public class HttpUtil {
    public static void main(String[] args) {

        try {

            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey

            Credential cred = new Credential("AKIDPBgJvQ7eohV3pKFWCZ0KMHzRcYmnNJlW", "d06E1oLgcGdjridHelbgFeoiKf162hYF");

            // 实例化一个http选项，可选的，没有特殊需求可以跳过

            HttpProfile httpProfile = new HttpProfile();

            httpProfile.setReqMethod("POST"); // get请求(默认为post请求)

            httpProfile.setConnTimeout(30); // 请求连接超时时间，单位为秒(默认60秒)

            httpProfile.setEndpoint("ocr.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)


            // 实例化一个client选项，可选的，没有特殊需求可以跳过

            ClientProfile clientProfile = new ClientProfile();

            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)

            clientProfile.setHttpProfile(httpProfile);


            // 实例化要请求产品(以cvm为例)的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

            DriverLicenseOCRRequest req = new DriverLicenseOCRRequest();
            req.setImageUrl("https://www.enablue.com/ROOT1/image/1588907533596批注.jpg");
            req.set("Action","EduPaperOCR");

            DriverLicenseOCRResponse resp = client.DriverLicenseOCR(req);
            // 输出json格式的字符串回包
            System.out.println(DriverLicenseOCRResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {

            System.out.println(e.toString());

        }
    }
}
