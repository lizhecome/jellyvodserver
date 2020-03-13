package com.alivc.longVideo.service.impl;

import com.alivc.base.ConfigMapUtil;
import com.alivc.base.VodOpenAPI;
import com.alivc.longVideo.service.VodService;
import com.aliyuncs.vod.model.v20170321.SubmitAIJobResponse;
import com.aliyuncs.vod.model.v20170321.SubmitTranscodeJobsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VodServiceImpl implements VodService {
    @Async
    @Override
    public void submitVodJob(String mediaId) {

        boolean flag = true;
        int sleepTime = 3000;
        //发布完长视频之后，提交截图和审核作业
        while (flag && sleepTime <= 9000) {
            try {
                VodOpenAPI.submitSnapshotJobs(mediaId, 0L, 0L, 1L);
                VodOpenAPI.submitSnapshotJobs(mediaId, 0L, 0L, 8L);
                if (ConfigMapUtil.getValueByKey("AUDIT_SETTINGS_FLAG") != null && "on".equals(ConfigMapUtil.getValueByKey("AUDIT_SETTINGS_FLAG"))) {
                    SubmitAIJobResponse aiJobResponse = VodOpenAPI.submitAIJob(mediaId, "AIVideoCensor");
                    log.info("【先审后发】 videoId：" + mediaId + "requestId:" + aiJobResponse.getRequestId());
                } else if (ConfigMapUtil.getValueByKey("AUDIT_SETTINGS_FLAG") != null && "off".equals(ConfigMapUtil.getValueByKey("AUDIT_SETTINGS_FLAG"))) {
                    SubmitTranscodeJobsResponse transcodeJobsResponse = VodOpenAPI.submitTranscodeJobs(mediaId, ConfigMapUtil.getValueByKey("LONGVIDEO_TRANSCODE_TEMPLATEGROUP_ID").trim(), mediaId);
                    log.info("【先发后审】videoId：" + mediaId + "requestId:" + transcodeJobsResponse.getRequestId());
                }
                flag = false;
                log.info("--- 提交截图，审核/转码完成");
                Thread.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                log.info("--- 提交截图，审核中");
                Thread.sleep(sleepTime);
                sleepTime = sleepTime + 3000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
