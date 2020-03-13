package com.alivc.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
public class CommRequestParam {

    /**
     * 应⽤名称
     */
    private String appName;

    /**
     * iOS应⽤的bundleId
     */
    private String bundleId;

    /**
     * Android应⽤包名
     */
    private String packageName;

    /**
     * Android签名
     */
    private String packageSignature;


    private String appVersionName;

    private Long appVersionCode;


    public static CommRequestParam newInstance(HttpServletRequest request) {
        CommRequestParam commRequestParam = new CommRequestParam();

        commRequestParam.setAppName(request.getHeader("appName"));
        commRequestParam.setBundleId(request.getHeader("bundleId"));
        commRequestParam.setPackageName(request.getHeader("packageName"));
        commRequestParam.setPackageSignature(request.getHeader("packageSignature"));
        commRequestParam.setAppVersionName(request.getHeader("appVersionName"));
        if (StringUtils.isNotBlank(request.getHeader("appVersionCode"))) {
            commRequestParam.setAppVersionCode(Long.parseLong(request.getHeader("appVersionCode")));
        }
        return commRequestParam;
    }

}
