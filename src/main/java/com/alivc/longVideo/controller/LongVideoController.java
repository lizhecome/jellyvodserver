package com.alivc.longVideo.controller;

import com.alivc.base.ConstanData;
import com.alivc.base.ResponseResult;
import com.alivc.base.TraceIdContext;
import com.alivc.longVideo.pojo.*;
import com.alivc.longVideo.service.DotService;
import com.alivc.longVideo.service.LongVideoService;
import com.alivc.longVideo.service.TagService;
import com.alivc.longVideo.service.TvPlayService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LongVideoController <br/>
 * Function: 长视频管理控制器. <br/>
 * Reason:   长视频相关业务视频管理. <br/>
 * Date:     2019年6月21日  <br/>
 *
 * @author tz
 * @version v0.0.1
 * @see
 * @since JDK 1.8
 */
@RestController
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class LongVideoController {

    private static final Logger LOG = LoggerFactory.getLogger(LongVideoController.class);
    @Autowired
    private TvPlayService tvPlayService;
    @Autowired
    private LongVideoService longVideoService;
    @Autowired
    private TagService tagService;
    @Autowired
    private DotService dotService;

    /**
     * 获取推荐电视剧
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getRecommnedTvPlayList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getRecommnedTvPlayList(String token, int pageIndex, int pageSize) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<TvPlay> tvPlayList = new ArrayList<TvPlay>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            TvPlayParm tvPlayParm = new TvPlayParm();
            tvPlayParm.setPageIndex((pageIndex - 1) * pageSize);
            tvPlayParm.setPageSize(pageSize);
            tvPlayParm.setIsRecommend("true");
            tvPlayParm.setIsRelease("true");
            tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
            int total = tvPlayService.selectTvPlayNums(tvPlayParm);
            map.put("total", total);
            map.put("tvPlayList", tvPlayList);
            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取推荐电视剧列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取首页电视剧
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getHomePageTvPlayList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getHomePageTvPlayList(String token, int pageIndex, int pageSize) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<Tag> tagsList = tagService.getTagsListByType("2");
            List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>(tagsList.size());
            Map<String, Object> tvPlayListMap = new HashMap<String, Object>(1);
            for (int i = 0; i < tagsList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>(3);
                List<TvPlay> tvPlayList = new ArrayList<TvPlay>();
                TvPlayParm tvPlayParm = new TvPlayParm();
                tvPlayParm.setPageIndex((pageIndex - 1) * pageSize);
                tvPlayParm.setPageSize(pageSize);
                tvPlayParm.setIsHomePage("true");
                tvPlayParm.setIsRelease("true");
                tvPlayParm.setTags(tagsList.get(i).getTagName());
                tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
                int total = tvPlayService.selectTvPlayNums(tvPlayParm);
                map.put("total", total);
                map.put("tag", tagsList.get(i).getTagName());
                map.put("tvPlayList", tvPlayList);
                if (!tvPlayList.isEmpty()) {
                    listMaps.add(map);
                }

            }
            tvPlayListMap.put("tagTvPlayList", listMaps);
            result.setData(tvPlayListMap);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取推荐电视剧列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取推荐长视频列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getRecommendLongVideosList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getRecommendLongVideosList(String token, int pageIndex, int pageSize) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> videoList = new ArrayList<LongVideo>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setPageIndex((pageIndex - 1) * pageSize);
            longVideoParm.setPageSize(pageSize);
            longVideoParm.setIsRecommend("true");
            videoList = longVideoService.getLongVideoList(longVideoParm);
            for (int i = 0; i < videoList.size(); i++) {
                List<Dot> dotList = new ArrayList<Dot>();
                dotList = dotService.getTotListById(videoList.get(i).getVideoId());
                videoList.get(i).setDotList(dotList);
            }
            int total = longVideoService.selectLongVideoNums(longVideoParm);
            map.put("total", total);
            map.put("videoList", videoList);
            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取推荐长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取首页长视频列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getLongVideosList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getLongVideosList(String token, int pageIndex, int pageSize) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<Tag> tagsList = tagService.getTagsListByType("1");
            List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>(tagsList.size());
            Map<String, Object> longVideoListMap = new HashMap<String, Object>(1);
            for (int i = 0; i < tagsList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>(3);
                List<LongVideo> videoList = new ArrayList<LongVideo>();
                LongVideoParm longVideoParm = new LongVideoParm();
                longVideoParm.setPageIndex((pageIndex - 1) * pageSize);
                longVideoParm.setPageSize(pageSize);
                longVideoParm.setTags(tagsList.get(i).getTagName());
                longVideoParm.setIsHomePage("true");
                longVideoParm.setIsRelease("true");
                videoList = longVideoService.getLongVideoList(longVideoParm);
                for (int j = 0; j < videoList.size(); j++) {
                    List<Dot> dotList = new ArrayList<Dot>();
                    dotList = dotService.getTotListById(videoList.get(j).getVideoId());
                    videoList.get(j).setDotList(dotList);
                }
                int total = longVideoService.selectLongVideoNums(longVideoParm);
                map.put("total", total);
                map.put("tag", tagsList.get(i).getTagName());
                map.put("videoList", videoList);
                if (!videoList.isEmpty()) {
                    listMaps.add(map);
                }
            }
            longVideoListMap.put("tagVideoList", listMaps);
            result.setData(longVideoListMap);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取首页长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取vip长视频列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getVipLongVideosList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getVipLongVideosList(String token, int pageIndex, int pageSize) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<Tag> tagsList = tagService.getTagsListByType("3");
            List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>(tagsList.size());
            Map<String, Object> longVideoListMap = new HashMap<String, Object>(1);
            for (int i = 0; i < tagsList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>(3);
                List<LongVideo> videoList = new ArrayList<LongVideo>();
                LongVideoParm longVideoParm = new LongVideoParm();
                longVideoParm.setPageIndex((pageIndex - 1) * pageSize);
                longVideoParm.setPageSize(pageSize);
                longVideoParm.setTags(tagsList.get(i).getTagName());
                longVideoParm.setIsVip("true");
                longVideoParm.setIsRelease("true");
                videoList = longVideoService.getLongVideoList(longVideoParm);
                for (int j = 0; j < videoList.size(); j++) {
                    List<Dot> dotList = new ArrayList<Dot>();
                    dotList = dotService.getTotListById(videoList.get(j).getVideoId());
                    videoList.get(j).setDotList(dotList);
                }
                int total = longVideoService.selectLongVideoNums(longVideoParm);
                map.put("total", total);
                map.put("tag", tagsList.get(i).getTagName());
                map.put("videoList", videoList);
                if (!videoList.isEmpty()) {
                    listMaps.add(map);
                }
            }
            longVideoListMap.put("tagVideoList", listMaps);
            result.setData(longVideoListMap);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取Vip长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 获取相似电视剧列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getSimilarTvPlayList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getSimilarTvPlayList(String token, String tvId) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,videoId: {}", traceId, tvId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<TvPlay> tvPlayList = new ArrayList<TvPlay>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            TvPlayParm tvPlayParm = new TvPlayParm();
            tvPlayParm.setPageIndex(0);
            tvPlayParm.setPageSize(5);
            tvPlayParm.setIsRelease("true");
            tvPlayParm.setTvId(tvId);
            tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
            List<TvPlay> allTvPlayList = new ArrayList<TvPlay>();
            if (!tvPlayList.isEmpty()) {
                String[] tagArray = tvPlayList.get(0).getTags().split(",");
                int total = 0;
                for (int i = 0; i < tagArray.length; i++) {
                    List<TvPlay> tagTvPlayList = new ArrayList<TvPlay>();
                    TvPlayParm tagsTvPlayParm = new TvPlayParm();
                    tagsTvPlayParm.setTags(tvPlayList.get(0).getTags());
                    tagsTvPlayParm.setPageSize(100);
                    tagsTvPlayParm.setPageIndex(0);
                    tagsTvPlayParm.setIsRelease("true");
                    tagTvPlayList = tvPlayService.getTvPlayList(tagsTvPlayParm);
                    total = tvPlayService.selectTvPlayNums(tagsTvPlayParm) + total;
                    allTvPlayList.addAll(tagTvPlayList);
                }
                map.put("total", total);
                map.put("tvPlayList", allTvPlayList);
            }

            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取相似电视剧列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取相似长视频列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getSimilarLongVideosList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getSimilarLongVideosList(String token, String videoId) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,videoId: {}", traceId, videoId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> videoList = new ArrayList<LongVideo>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setPageIndex(0);
            longVideoParm.setPageSize(100);
            longVideoParm.setIsRelease("true");
            longVideoParm.setVideoId(videoId);
            longVideoParm.setTvId(null);
            videoList = longVideoService.getLongVideoList(longVideoParm);
            List<LongVideo> allVideoList = new ArrayList<LongVideo>();
            if (!videoList.isEmpty()) {
                String[] tagArray = videoList.get(0).getTags().split(",");
                int total = 0;
                for (int i = 0; i < tagArray.length; i++) {
                    List<LongVideo> tagVideoList = new ArrayList<LongVideo>();
                    LongVideoParm tagsLongVideoParm = new LongVideoParm();
                    tagsLongVideoParm.setPageIndex(0);
                    tagsLongVideoParm.setPageSize(5);
                    tagsLongVideoParm.setTags(videoList.get(0).getTags());
                    tagsLongVideoParm.setIsVip(videoList.get(0).getIsVip());
                    tagsLongVideoParm.setIsRelease("true");
                    tagsLongVideoParm.setTvId(null);
                    tagsLongVideoParm.setOnlyVideo(true);
                    tagVideoList = longVideoService.getLongVideoList(tagsLongVideoParm);
                    for (int j = 0; j < tagVideoList.size(); j++) {
                        List<Dot> dotList = new ArrayList<Dot>();
                        dotList = dotService.getTotListById(tagVideoList.get(j).getVideoId());
                        tagVideoList.get(j).setDotList(dotList);
                    }
                    total = longVideoService.selectLongVideoNums(tagsLongVideoParm) + total;
                    allVideoList.addAll(tagVideoList);
                }
                map.put("total", total);
                map.put("videoList", allVideoList);
            }

            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取相似长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 根据电视剧id查询长视频列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getLongVideoById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getLongVideoById(String token, String tvId) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ", traceId, tvId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> videoList = new ArrayList<LongVideo>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setTvId(tvId);
            videoList = longVideoService.getLongVideoList(longVideoParm);
            for (int j = 0; j < videoList.size(); j++) {
                List<Dot> dotList = new ArrayList<Dot>();
                dotList = dotService.getTotListById(videoList.get(j).getVideoId());
                videoList.get(j).setDotList(dotList);
            }
            int total = videoList.size();
            map.put("total", total);
            map.put("videoList", videoList);
            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取Vip电视剧列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @param tvId 剧集id
     * @return 剧集list
     */
    @RequestMapping(value = "/longVideo/tvPlayList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getTvPlayList(String tvId) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} tvId: {}", traceId, tvId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            Map<String, Object> map = new HashMap<String, Object>(2);
            TvPlayParm tvPlayParm = new TvPlayParm();
            tvPlayParm.setTvId(tvId);
            tvPlayParm.setPageIndex(0);
            tvPlayParm.setPageSize(10);
            List<TvPlay> tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
            map.put("tvPlayList", tvPlayList);
            result.setMessage("根据条件获取剧集列表完成");
            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 根据条件获取剧集列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 根据类型获取标签
     *
     * @param
     * @return result
     * @throws UnknownHostException
     */
    @RequestMapping(value = "/longVideo/getTagsListByType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getTagsListByType(String token, String type) throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {}", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            if (StringUtils.isEmpty(type)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("type为必填项");
                return result;
            }
            List<Tag> tagList = new ArrayList<Tag>();
            tagList = tagService.getTagsListByType(type);
            result.setMessage("获取标签完成");
            result.setData(tagList);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("---获取标签异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 根据标签获取vip长视频列表
     *
     * @param token，pageIndex，pageSize
     * @return result
     */
    @RequestMapping(value = "/longVideo/getVipLongVideosListbyTag", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getVipLongVideosListbyTag(String token, String tag, int pageIndex, int pageSize) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            Map<String, Object> longVideoListMap = new HashMap<String, Object>(1);
            Map<String, Object> map = new HashMap<String, Object>(3);
            List<LongVideo> videoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setPageIndex((pageIndex - 1) * pageSize);
            longVideoParm.setPageSize(pageSize);
            longVideoParm.setTags(tag);
            longVideoParm.setIsVip("true");
            longVideoParm.setIsRelease("true");
            videoList = longVideoService.getLongVideoList(longVideoParm);
            for (int j = 0; j < videoList.size(); j++) {
                List<Dot> dotList = new ArrayList<Dot>();
                dotList = dotService.getTotListById(videoList.get(j).getVideoId());
                videoList.get(j).setDotList(dotList);
            }
            int total = longVideoService.selectLongVideoNums(longVideoParm);
            map.put("total", total);
            map.put("videoList", videoList);
            longVideoListMap.put("longVideoList", map);
            result.setData(longVideoListMap);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 获取Vip长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


}