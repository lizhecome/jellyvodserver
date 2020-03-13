package com.alivc.interceptor;

import com.alibaba.fastjson.JSON;
import com.alivc.base.ConfigMapUtil;
import com.alivc.base.TraceIdContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class PackInterceptor implements HandlerInterceptor {


    private static final String[] PACKAGE_NAMES = StringUtils.split(ConfigMapUtil.getValueByKey("package_name"), ",");
    private static final List<String> PACKAGE_NAMES_LIST = Arrays.asList(PACKAGE_NAMES);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        // 线程绑定变量（该数据只有当前请求的线程可见）
        TraceIdContext.ctx.remove();
        String traceId = TraceIdContext.ctx.get().getTraceId();

        CommRequestParam param = CommRequestParam.newInstance(request);

        if (isAllow(param)) {
            return true;
        } else {
            log.error("Forbidden : {},PACKAGE_NAME : {}", traceId, JSON.toJSONString(param));

            PrintWriter out = response.getWriter();
            String resultJson = "{\"code\":\"403\",\"message\":\"Please Configure the correct Pack!\"}";
            out.append(resultJson);
            response.setStatus(403);

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }

    private boolean isAllow(CommRequestParam param) {

        String packageName;
        if (StringUtils.isNotBlank(param.getBundleId())) {
            packageName = param.getBundleId();
        } else if (StringUtils.isNotBlank(param.getPackageName())) {
            packageName = param.getPackageName();
        } else {
            return true;
        }

        return PACKAGE_NAMES_LIST.contains(packageName);

    }


}