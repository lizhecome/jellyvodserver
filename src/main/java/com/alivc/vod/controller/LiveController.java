package com.alivc.vod.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import com.alivc.base.AuthTokenGetUtil;
import com.alivc.base.ConfigMapUtil;
import com.alivc.base.ConstanData;
import com.alivc.base.ResponseResult;
import com.alivc.base.TraceIdContext;
import com.alivc.base.VodOpenAPI;
import com.alivc.user.pojo.BaseUserProfile;
import com.alivc.vod.pojo.PersonageVideo;
import com.alivc.vod.pojo.RecommendLive;
import com.alivc.vod.pojo.RecommendVideo;
import com.alivc.vod.service.LiveService;
import com.alivc.vod.service.VodVideoService;
import com.aliyuncs.vod.model.v20170321.SubmitAIJobResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LiveController <br/>
 * Function:播流管理控制器. <br/>
 * Reason:   播流管理. <br/>
 * Date:     2019年5月15日  <br/>
 *
 * @author tz
 * @version v0.0.1
 * @see
 * @since JDK 1.8
 */
@RestController
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class LiveController {

    private static final Logger LOG = LoggerFactory.getLogger(LiveController.class);
    @Autowired
    private LiveService liveService;
    
 
    /**
     * 获取推荐视频
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/vod/getRecommendLiveList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getRecommendVideo(String token, int pageIndex, int pageSize) {

        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);

        ResponseResult result = new ResponseResult(traceId);
        try {
            List<RecommendLive> liveList = new ArrayList<RecommendLive>();
            Map<String, Object> map = new HashMap<String, Object>(1);
            liveList = liveService.getRecommendLive(pageIndex, pageSize);
            map.put("liveList", liveList);
            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取播流列表异常", e);
        }
        return result;
    }
 

}