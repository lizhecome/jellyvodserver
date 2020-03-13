package com.alivc.vod.service.impl;

import com.alivc.base.AuthKeyUrlUtil;
import com.alivc.vod.dao.LiveDao;
import com.alivc.vod.pojo.RecommendLive;
import com.alivc.vod.service.LiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于实现播流service层的方法
 */
@Service
public class LiveServiceImpl implements LiveService {
    private static final Logger LOG = LoggerFactory.getLogger(LiveServiceImpl.class);
    @Resource
    private LiveDao liveDao;


    /**
     * 获取推荐播流列表
     *
     * @param pageIndex, pageSize
     * @return List<RecommendVideo>
     */
    @Override
    public List<RecommendLive> getRecommendLive(int pageIndex, int pageSize) {
        List<RecommendLive> videoList = new ArrayList<RecommendLive>();
        try {
            videoList = liveDao.getRecommendLive((pageIndex - 1) * pageSize, pageSize);
            for (RecommendLive recommendLive : videoList) {
                String fileName = recommendLive.getLiveUrl();
                String url = AuthKeyUrlUtil.getAuthUrl(fileName);
                recommendLive.setLiveUrl(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---获取推荐视频异常", e);
        }
        return videoList;
    }

}
