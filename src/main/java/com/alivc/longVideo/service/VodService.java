package com.alivc.longVideo.service;

import org.springframework.scheduling.annotation.Async;

public interface VodService {


    /**
     * 提交截图审核任务
     * @param mediaId  videoId
     */
    @Async
    void submitVodJob(String mediaId);
}
