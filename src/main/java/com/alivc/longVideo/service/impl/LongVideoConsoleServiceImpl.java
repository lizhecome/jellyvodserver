package com.alivc.longVideo.service.impl;

import com.alivc.base.RandomString;
import com.alivc.longVideo.dao.LongVideoConsoleDao;
import com.alivc.longVideo.pojo.LongVideo;
import com.alivc.longVideo.pojo.LongVideoParm;
import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;
import com.alivc.longVideo.service.LongVideoConsoleService;
import com.alivc.longVideo.service.VodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * 长视频控制台impl层
 * 用于实现长视频控制台service层的方法
 */
@Service
public class LongVideoConsoleServiceImpl implements LongVideoConsoleService {
    private static final Logger LOG = LoggerFactory.getLogger(LongVideoConsoleServiceImpl.class);
    @Resource
    private LongVideoConsoleDao longVideoConsoleDao;
    @Resource
    private VodService vodService;


    /**
     * 长视频上传
     *
     * @param
     * @return
     */
    @Override
    public void longVideoUpload(String title, String videoId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            longVideo.setTitle(title);
            longVideo.setTranscodeStatus("onTranscode");
            longVideo.setSnapshotStatus("onSnapshot");
            longVideo.setCensorStatus("onCensor");
            longVideo.setIsVip("false");
            longVideo.setIsHomePage("false");
            longVideo.setIsRecommend("false");
            longVideo.setCreationTime(format.format(System.currentTimeMillis()));
            longVideoConsoleDao.insertLongVideo(longVideo);


            //发布完视频之后，进入审核阶段，如果提交审核失败，sleep3秒后继续进入提交审核
            vodService.submitVodJob(videoId);

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---长视频上传异常", e);
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public int updateLongVideo(LongVideo longVideo) {
        int update = 0;
        try {
            update = longVideoConsoleDao.updateLongVideo(longVideo);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---更新长视频异常", e);
        }
        return update;
    }

    @Override
    public int insertTvPlay(TvPlayParm tvPlayParm) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int insert = 0;
        try {
            //String coverUrl = VodOpenAPI.uploadImageFile("cover",tvPlayParm.getFileName());
            //String coverUrl = VodOpenAPI.uploadImageStream("cover",tvPlayParm.getFileName());
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTvId(RandomString.getData());
            tvPlay.setTvName(tvPlayParm.getTvName());
            tvPlay.setTitle(tvPlayParm.getTitle());
            tvPlay.setCoverUrl(tvPlayParm.getCoverUrl());
            tvPlay.setDescription(tvPlayParm.getDescription());
            tvPlay.setTags(tvPlayParm.getTags());
            tvPlay.setTotal("0");
            tvPlay.setIsRecommend("false");
            tvPlay.setIsRelease("false");
            tvPlay.setIsHomePage("false");
            tvPlay.setCreationTime(format.format(System.currentTimeMillis()));
            insert = longVideoConsoleDao.insertTvPlay(tvPlay);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---新建电视剧异常", e);
        }
        return insert;

    }

    @Override
    public void deleteTvPlayById(String tvId) {
        try {
            longVideoConsoleDao.deleteTvPlayById(tvId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---删除电视剧异常", e);
        }

    }

    @Override
    public void deleteLongVideoById(String videoId) {
        try {
            longVideoConsoleDao.deleteLongVideoById(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---删除长视频异常", e);
        }

    }

    @Override
    public int updateTvPlay(TvPlay tvPlay) {
        int update = 0;
        try {

            update = longVideoConsoleDao.updateTvPlay(tvPlay);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---上下架电视剧异常", e);
        }
        return update;
    }

    @Override
    public int updateLongVideoSort(LongVideo longVideo) {
        int update = 0;
        try {
            update = longVideoConsoleDao.updateLongVideoSort(longVideo);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---修改大于某序号的长视频序号异常", e);
        }
        return update;
    }

    @Override
    public int updateTvPlayTotal(String tvId) {
        int update = 0;
        try {
            update = longVideoConsoleDao.updateTvPlayTotal(tvId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---修改电视剧总集数异常", e);
        }
        return update;
    }

    @Override
    public int subtractLongVideoSort(LongVideoParm longVideo) {
        int update = 0;
        try {
            update = longVideoConsoleDao.subtractLongVideoSort(longVideo);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---对某区间长视频序号修改异常", e);
        }
        return update;
    }

    @Override
    public int addLongVideoSort(LongVideoParm longVideoParm) {
        int update = 0;
        try {
            update = longVideoConsoleDao.addLongVideoSort(longVideoParm);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---对某区间长视频序号修改异常", e);
        }
        return update;
    }

}
