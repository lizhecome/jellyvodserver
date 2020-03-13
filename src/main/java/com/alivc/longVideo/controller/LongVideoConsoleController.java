package com.alivc.longVideo.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alivc.base.*;
import com.alivc.longVideo.pojo.*;
import com.alivc.longVideo.service.*;
import com.alivc.vod.pojo.VideoSnapshot;
import com.alivc.vod.service.VodVideoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LongVideoConsoleController <br/>
 * Function: TODO 长视频控制台管理控制器. <br/>
 * Reason: TODO 长视频控制台管理. <br/>
 * Date: 2019年6月24日 <br/>
 * 
 * @author tz
 * @version v0.0.1
 * @since JDK 1.8
 * @see
 */
@RestController
public class LongVideoConsoleController {

    private static final Logger LOG = LoggerFactory.getLogger(LongVideoConsoleController.class);
    @Autowired
    private LongVideoConsoleService longVideoConsoleService;
    @Autowired
    private LongVideoService longVideoService;
    @Autowired
    private TvPlayService tvPlayService;
    @Autowired
    private TagService tagService;
    @Autowired
    private VodVideoService vodService;
    @Autowired
    private DotService dotService;

    /**
     *  控制台上传长视频
     * 
     * @param   
     * @return result
     * @throws UnknownHostException 
     */
    @RequestMapping(value = "/console/longVideo/longVideoUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult longVideoUpload(String consoleToken,String title, String videoId) throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {},videoId: {}", traceId,videoId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            longVideoConsoleService.longVideoUpload(title,videoId);
            result.setMessage("上传长视频完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 控制台上传长视频异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     *  控制台新建电视剧
     *
     * @param
     * @return result
     * @throws UnknownHostException
     */
    @RequestMapping(value = "/console/longVideo/createTvPlay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult createTvPlay(String consoleToken,String title, String description, String tvName, String tags,  MultipartFile file) throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {}", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            if (StringUtils.isEmpty(title)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("title为必填项");
                return result;
            }
            if (StringUtils.isEmpty(tvName)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("tvName为必填项");
                return result;
            }
            String coverUrl = VodOpenAPI.uploadImageStream("cover",file.getInputStream());

            TvPlayParm tvPlayParm = new TvPlayParm();
            tvPlayParm.setTitle(title);
            tvPlayParm.setTvName(tvName);
            tvPlayParm.setDescription(description);
            tvPlayParm.setTags(tags);
            tvPlayParm.setCoverUrl(coverUrl);
            longVideoConsoleService.insertTvPlay(tvPlayParm);
            result.setMessage("新建电视剧完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 新建电视剧异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 编辑长视频基本信息
     *
     * @param consoleToken，videoId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/updateLongVideoInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setLongViupdateLongVideoInfodeoHomePage(String consoleToken,  String videoId,String tags ,String title,String description ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {},tags:  {} ", traceId,  videoId,tags );
        ResponseResult result = new ResponseResult(traceId);
        try {
            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            longVideo.setTags(tags);
            longVideo.setTitle(title);
            longVideo.setDescription(description);
            longVideoConsoleService.updateLongVideo(longVideo);
            result.setMessage("编辑长视频基本信息完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 编辑长视频基本信息异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  控制台编辑电视剧
     *
     * @param
     * @return result
     * @throws UnknownHostException
     */
    @RequestMapping(value = "/console/longVideo/updateTvPlayInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateTvPlayInfo(String consoleToken,String tvId,String title, String description, String tvName, String tags,  MultipartFile file) throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {}", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            if (StringUtils.isEmpty(tvId)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("tvId为必填项");
                return result;
            }
            TvPlay tvPlay = new TvPlay();
            if(file != null &&  !file.isEmpty()){
                String coverUrl = VodOpenAPI.uploadImageStream("cover",file.getInputStream());
                tvPlay.setCoverUrl(coverUrl);
            }
            if(title != null && !title.isEmpty()){
                tvPlay.setTitle(title);
            }
            if(description != null && !description.isEmpty()){
                tvPlay.setDescription(description);
            }
            if(tvName != null && !tvName.isEmpty()){
                tvPlay.setTvName(tvName);
            }
            if(tags != null && !tags.isEmpty()){
                tvPlay.setTags(tags);
            }
            tvPlay.setTvId(tvId);
            longVideoConsoleService.updateTvPlay(tvPlay);
            result.setMessage("编辑电视剧完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 修改电视剧完成", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 根据条件获取长视频列表
     *
     * @param consoleToken，pageIndex，pageSize
      videoId,  title,  startTime,   endTime,  censorStatus
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/getLongVideos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getLongVideos(String consoleToken, int pageIndex, int pageSize, String isRecommend,String isVip,String isHomePage,
                                                     String videoId,String title,String startTime,String  endTime,String censorStatus,String tags ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> videoList = new ArrayList<LongVideo>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setPageIndex((pageIndex-1)*pageSize);
            longVideoParm.setPageSize(pageSize);
            longVideoParm.setIsRecommend(isRecommend);
            longVideoParm.setIsVip(isVip);
            longVideoParm.setIsHomePage(isHomePage);
            longVideoParm.setVideoId(videoId);
            longVideoParm.setTitle(title);
            longVideoParm.setStartTime(startTime);
            longVideoParm.setEndTime(endTime);
            longVideoParm.setTags(tags);
            longVideoParm.setCensorStatus(censorStatus);
            videoList = longVideoService.getLongVideoList(longVideoParm);
            for(int i = 0;i < videoList.size();i ++){
                List<String> snapshotList = new ArrayList<String>();
                List<VideoSnapshot> videoSnapshotList = vodService.getSnapshotById(videoList.get(i).getVideoId());
                for(int j = 0;j < videoSnapshotList.size();j ++){
                    snapshotList.add(videoSnapshotList.get(j).getSnapshotUrl());
                }
                videoList.get(i).setSnapshotList(snapshotList);
            }
            int total = longVideoService.selectLongVideoNums(longVideoParm);
            map.put("total", total);
            map.put("longVideoList", videoList);
            result.setData(map);
            result.setMessage("根据条件获取长视频列表完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 根据获取长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 根据条件获取电视剧列表
     *
     * @param consoleToken，pageIndex，pageSize,isRecommend,
     *          tvId, title, startTime,  endTime, isRelease
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/getTvPlayList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getTvPlayList(String consoleToken, int pageIndex, int pageSize, String isRecommend,String isHomePage,String tags,
                                        String tvId,String title,String startTime,String  endTime,String isRelease ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,pageIndex: {}, pageSize: {}", traceId, pageIndex, pageSize);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<TvPlay> tvPlayList = new ArrayList<TvPlay>();
            Map<String, Object> map = new HashMap<String, Object>(2);
            TvPlayParm tvPlayParm = new TvPlayParm();
            tvPlayParm.setPageIndex((pageIndex-1)*pageSize);
            tvPlayParm.setPageSize(pageSize);
            tvPlayParm.setIsRelease(isRelease);
            tvPlayParm.setIsRecommend(isRecommend);
            tvPlayParm.setIsHomePage(isHomePage);
            tvPlayParm.setTvId(tvId);
            tvPlayParm.setTitle(title);
            tvPlayParm.setStartTime(startTime);
            tvPlayParm.setEndTime(endTime);
            tvPlayParm.setTags(tags);
            tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
            int total = tvPlayService.selectTvPlayNums(tvPlayParm);
            map.put("total", total);
            map.put("tvPlayList", tvPlayList);
            result.setMessage("根据条件获取电视剧列表完成");
            result.setData(map);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 根据条件获取电视剧列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 设置长视频序号
     *
     * @param consoleToken，
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setLongVideoSort", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult getTvPlayList(String consoleToken, String videoId,
                                        String tvId, String tvName,String sort ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,videoId: {}, tvId: {}, tvName: {},sort: {}", traceId, videoId, tvName,tvId,sort);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setVideoId(videoId);
            longVideoList = longVideoService.getLongVideoList(longVideoParm);

            System.out.println("longVideoList.get(0).getTvId()"+"("+longVideoList.get(0).getTvId()+")");

            List<LongVideo> videoList = new ArrayList<LongVideo>();
            LongVideoParm videoParm = new LongVideoParm();
            videoParm.setVideoId(videoId);
            videoParm.setTvId(tvId);
            videoList = longVideoService.getLongVideoList(videoParm);

            LongVideo longVideo = new LongVideo();
            longVideo.setTvId(tvId);
            longVideo.setVideoId(videoId);
            longVideo.setSort(sort);
            if(longVideoList.isEmpty() || longVideoList.get(0).getTvId() == null || "".equals(longVideoList.get(0).getTvId().trim())){
                longVideoConsoleService.updateLongVideoSort(longVideo);
                TvPlayParm  tvPlayParm = new TvPlayParm();
                tvPlayParm.setPageIndex(0);
                tvPlayParm.setPageSize(5);
                tvPlayParm.setTvId(tvId);
                List<TvPlay> tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
                if(!tvPlayList.isEmpty()){
                    TvPlay tvPlay = new TvPlay();
                    tvPlay.setTotal((Integer.parseInt(tvPlayList.get(0).getTotal()) + 1) + "");
                    tvPlay.setTvId(tvId);
                    longVideoConsoleService.updateTvPlay(tvPlay);
                }
                longVideo.setTvName(tvName);
                longVideoConsoleService.updateLongVideo(longVideo);
            }else if(!longVideoList.isEmpty() && longVideoList.get(0).getTvId().equals(tvId)){
                if(Integer.parseInt(videoList.get(0).getSort()) < Integer.parseInt(sort)){
                    LongVideoParm subParm = new LongVideoParm();
                    subParm.setSort(videoList.get(0).getSort());
                    subParm.setUpdateSort(sort);
                    longVideoConsoleService.subtractLongVideoSort(subParm);
                }else{
                    LongVideoParm sortParm = new LongVideoParm();
                    sortParm.setSort(videoList.get(0).getSort());
                    sortParm.setUpdateSort(sort);
                    longVideoConsoleService.addLongVideoSort(sortParm);
                }
                longVideo.setTvName(tvName);
                longVideoConsoleService.updateLongVideo(longVideo);
            }else if(!longVideoList.isEmpty() && longVideoList.get(0).getTvId() != null &&!"".equals(longVideoList.get(0).getTvId().trim()) && !longVideoList.get(0).getTvId().equals(tvId)){
                result.setCode(ConstanData.UNPROCESABLE_ENTITY);
                result.setResult("false");
                result.setMessage("该视频已经配置其他电视剧，无法与该添加到当前电视剧");
                return result;
            }
            result.setMessage("设置长视频序号完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 设置长视频序号异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 解除长视频与电视剧关联
     *
     * @param consoleToken，
     * @return result
     */
        @RequestMapping(value = "/console/longVideo/relieveLongVideoSort", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult relieveLongVideoSort(String consoleToken, String videoId,
                                        String tvId, String sort ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ,videoId: {}, tvId: {}, sort: {}", traceId, videoId, tvId,sort);
        ResponseResult result = new ResponseResult(traceId);
        try {
            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            longVideo.setTvName(" ");
            longVideo.setTvId(" ");
            longVideo.setSort(" ");
            longVideoConsoleService.updateLongVideo(longVideo);

            LongVideoParm subParm = new LongVideoParm();
            subParm.setSort(sort);
            longVideoConsoleService.subtractLongVideoSort(subParm);

            TvPlayParm  tvPlayParm = new TvPlayParm();
            tvPlayParm.setPageIndex(0);
            tvPlayParm.setPageSize(5);
            tvPlayParm.setTvId(tvId);
            List<TvPlay> tvPlayList = tvPlayService.getTvPlayList(tvPlayParm);
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTotal((Integer.parseInt(tvPlayList.get(0).getTotal()) - 1) + "");
            tvPlay.setTvId(tvId);
            longVideoConsoleService.updateTvPlay(tvPlay);

            result.setMessage("解除长视频与电视剧关联完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 解除长视频与电视剧关联异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 删除电视剧
     *
     * @param consoleToken，tvId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/deleteTvPlay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteTvPlay(String consoleToken,  String tvId  ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , tvId: {} ", traceId,  tvId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            longVideoConsoleService.deleteTvPlayById(tvId);
            result.setMessage("删除电视剧完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 删除电视剧异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 删除长视频
     *
     * @param consoleToken，tvId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/deleteLongVideo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteLongVideo(String consoleToken,  String videoId) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {} ", traceId,  videoId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setVideoId(videoId);
            longVideoList = longVideoService.getLongVideoList(longVideoParm);
            if(!longVideoList.isEmpty() && longVideoList.get(0).getTvId() != null && !"".equals(longVideoList.get(0).getTvId().trim())  ){
                result.setCode(ConstanData.UNPROCESABLE_ENTITY);
                result.setResult("false");
                result.setMessage("该视频已经配置电视剧，请先解除电视剧绑定");
                return result;
            }
            longVideoConsoleService.deleteLongVideoById(videoId);
            result.setMessage("删除长视频完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 删除长视频异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 上下架电视剧
     *
     * @param consoleToken，
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/updateTvPlayReleaseStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateTvPlayReleaseStatus(String consoleToken,String tvId,String isRelease ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , tvId: {},isRelease: {}", traceId, tvId,isRelease);
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideParm = new LongVideoParm();
                longVideParm.setTvId(tvId);
                longVideoList = longVideoService.getLongVideoList(longVideParm);
                for(int i = 0;i < longVideoList.size();i ++) {
                    if (!longVideoList.get(i).getCensorStatus().isEmpty() && !"success".equals(longVideoList.get(i).getCensorStatus())) {
                        result.setResult("false");
                        result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                        result.setMessage("该电视剧中的视频"+longVideoList.get(i).getTvName()+"审核状态为"+longVideoList.get(i).getCensorStatus()+",无法进行上架！");
                        return result;
                    }
                }
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTvId(tvId);
            tvPlay.setIsRelease(isRelease);
            longVideoConsoleService.updateTvPlay(tvPlay);
            result.setMessage("上下架电视剧完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 上下架电视剧异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 推送电视剧到首页位
     *
     * @param consoleToken，tvId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setTvPlayHomePage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setTvPlayHomePage(String consoleToken,  String tvId   ,String tags) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , tvId: {} ", traceId,  tvId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTvId(tvId);
            tvPlay.setTags(tags);
            tvPlay.setIsHomePage("true");
            longVideoConsoleService.updateTvPlay(tvPlay);
            result.setMessage("推送电视剧到首页位完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 推送电视剧到首页位异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 推送电视剧到推荐位
     *
     * @param consoleToken，tvId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setTvPlayRecommend", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setTvPlayRecommend(String consoleToken,  String tvId ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , tvId: {} ", traceId,  tvId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTvId(tvId);
            tvPlay.setIsRecommend("true");
            longVideoConsoleService.updateTvPlay(tvPlay);
            result.setMessage("推送电视剧到推荐位完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 推送电视剧到推荐位异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 推送长视频到首页位
     *
     * @param consoleToken，videoId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setLongVideoHomePage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setLongVideoHomePage(String consoleToken,  String videoId,String tags  ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {},tags:  {} ", traceId,  videoId,tags );
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setVideoId(videoId);
            longVideoList = longVideoService.getLongVideoList(longVideoParm);

            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            if(!longVideoList.isEmpty() && "true".equals(longVideoList.get(0).getIsVip())){
                longVideo.setTags(" ");
            }
            longVideo.setIsVip("false");
            longVideo.setIsHomePage("true");
            longVideoConsoleService.updateLongVideo(longVideo);
            result.setMessage("推送长视频到首页位完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 推送电视剧到首页位异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 推送长视频到推荐位
     *
     * @param consoleToken，videoId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setLongVideoRecommend", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setLongVideoRecommend(String consoleToken,  String videoId  ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {} ", traceId,  videoId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setVideoId(videoId);
            longVideoList = longVideoService.getLongVideoList(longVideoParm);

            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            if(!longVideoList.isEmpty() && "true".equals(longVideoList.get(0).getIsVip())){
                longVideo.setTags(" ");
            }
            longVideo.setIsVip("false");
            longVideo.setIsRecommend("true");
            longVideoConsoleService.updateLongVideo(longVideo);
            result.setMessage("推送长视频到推荐位完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 推送长视频到推荐位异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 推送长视频到vip位
     *
     * @param consoleToken，videoId tags
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setLongVideoVip", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setLongVideoVip(String consoleToken,  String videoId,String tags  ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {},tags:  {} ", traceId,  videoId,tags );
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setVideoId(videoId);
            longVideoList = longVideoService.getLongVideoList(longVideoParm);

            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            if(!longVideoList.isEmpty() && ("true".equals(longVideoList.get(0).getIsHomePage()) || "true".equals(longVideoList.get(0).getIsRecommend()))){
                longVideo.setTags(" ");
            }
            longVideo.setIsHomePage("false");
            longVideo.setIsRecommend("false");
            longVideo.setIsVip("true");
            longVideoConsoleService.updateLongVideo(longVideo);
            result.setMessage("推送长视频到vip位完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 推送电视剧到vip位异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 取消长视频推荐
     *
     * @param consoleToken，videoId tags
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/updateLongVideoStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateLongVideoStatus(String consoleToken,  String videoId,String type  ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {},type:  {} ", traceId,  videoId,type );
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<LongVideo> longVideoList = new ArrayList<LongVideo>();
            LongVideoParm longVideoParm = new LongVideoParm();
            longVideoParm.setVideoId(videoId);
            longVideoList = longVideoService.getLongVideoList(longVideoParm);

            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            if(!type.isEmpty() && "1".equals(type)){
                if( "true".equals(longVideoList.get(0).getIsVip())){
                    longVideo.setTags(" ");
                }
                longVideo.setIsRecommend("false");
            } else if(!type.isEmpty() && "2".equals(type)){
                longVideo.setIsHomePage("false");
                if( "true".equals(longVideoList.get(0).getIsVip())){
                    longVideo.setTags(" ");
                }
            } else if(!type.isEmpty() && "3".equals(type)){
                longVideo.setIsVip("false");
            }

            longVideoConsoleService.updateLongVideo(longVideo);
            result.setMessage("取消长视频推荐完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 取消长视频推荐异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * 取消电视剧推荐
     *
     * @param consoleToken，videoId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/updateTvPlayStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateTvPlayStatus(String consoleToken,  String tvId,String type  ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , tvId: {},type:  {} ", traceId,  tvId,type );
        ResponseResult result = new ResponseResult(traceId);
        try {
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTvId(tvId);
            if(!type.isEmpty() && "1".equals(type)){
                tvPlay.setIsRecommend("false");
            } else if(!type.isEmpty() && "2".equals(type)){
                tvPlay.setIsHomePage("false");
            }
            tvPlay.setTags(" ");
            longVideoConsoleService.updateTvPlay(tvPlay);
            result.setMessage("取消电视剧推荐完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 取消电视剧推荐异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 设置长视频打点信息
     *
     * @param consoleToken，videoId
     * @return result
     */
        @RequestMapping(value = "/console/longVideo/setLongVideoDot", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setLongVideoDot(String consoleToken,String videoId,  String parms ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , parms: {} ", traceId,  parms );
        ResponseResult result = new ResponseResult(traceId);
            if (StringUtils.isEmpty(videoId)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("videoId为必填项");
                return result;
            }
        //List<Dot> dotList = JSONArray.fromObject(dotJsonList.get("goodsList")).toList(dotJsonList,Dot.class);
            JSONObject jsonObject = JSONObject.parseObject(parms); //将str转化为相应的JSONObject对象
            System.out.println(jsonObject);
            String str = jsonObject.getString("dotList"); //取出allFlows对应的值,值为字符串
            //使用JSONArray.parseArray(String, Class<T>)将字符串转为指定对象集合
            List<Dot> dotList = (List<Dot>) JSONArray.parseArray(str, Dot.class);
        try {
            dotService.deleteDotById(videoId);
            if(!dotList.isEmpty()){

                for(int i = 0;i < dotList.size();i ++){
                    dotService.insertDot(dotList.get(i));
                }
            }

            result.setMessage("设置长视频打点信息完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 设置长视频打点信息异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 根据视频id获取打点信息
     *
     * @param consoleToken，videoId
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/getDotListById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getDotListById(String consoleToken, String videoId   ) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {} ", traceId,  videoId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            List<Dot> dotList = dotService.getTotListById(videoId);
            result.setMessage("查询长视频打点信息完成");
            result.setData(dotList);
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 查询长视频打点信息异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 人工审核
     *
     * @param
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/createAudit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult createAudit(String consoleToken, String videoId, String status, String reason, String comment) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            VodOpenAPI.createAudit(videoId, status,reason, comment);
            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            if(StringUtils.isNotEmpty(status) && "Normal".equals(status)){
                longVideo.setCensorStatus("success");
                longVideoConsoleService.updateLongVideo(longVideo);
            }else if(StringUtils.isNotEmpty(status) && "Blocked".equals(status)){
                longVideo.setCensorStatus("fail");
                longVideoConsoleService.updateLongVideo(longVideo);
            }
            result.setMessage("人工审核完成！");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 人工审核异常", e);
        }
        return result;
    }

    /**
     *  新增标签信息
     *
     * @param
     * @return result
     * @throws UnknownHostException
     */
    @RequestMapping(value = "/console/longVideo/createTag", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult createTag(String consoleToken, String tagName, String type ) throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {}", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            if (StringUtils.isEmpty(tagName)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("tagName为必填项");
                return result;
            }

            if (StringUtils.isEmpty(type)) {
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                result.setMessage("type为必填项");
                return result;
            }
            List<Tag> tagList = tagService.getTagsList(type,tagName);
            if(!tagList.isEmpty()){
                result.setMessage("标签重复完成");
                result.setResult("false");
                result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
                return result;
            }

            Tag tag = new Tag();
            tag.setTagId(RandomString.getData());
            tag.setTagName(tagName);
            tag.setType(type);
            tagService.insertTag(tag);
            result.setMessage("新建标签完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("---新建标签异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     *  删除标签信息
     *
     * @param
     * @return result
     * @throws UnknownHostException
     */
    @RequestMapping(value = "/console/longVideo/deleteTag", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteTag(String consoleToken, String tagId) throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {}", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {

            tagService.deleteTagById(tagId);
            result.setMessage("删除标签信息");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("---删除标签信息", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  根据类型获取标签
     *
     * @param
     * @return result
     * @throws UnknownHostException
     */
    @RequestMapping(value = "/console/longVideo/getTagsListByType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getTagsListByType(String consoleToken,  String type ) throws UnknownHostException {
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
     *  设置电视剧标签
     *
     * @param consoleToken，tvId, tags
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setTvPlayTag", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setTvPlayTag(String consoleToken,  String tvId ,String tags) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , tvId: {} ", traceId,  tvId );
        ResponseResult result = new ResponseResult(traceId);
        try {
            TvPlay tvPlay = new TvPlay();
            tvPlay.setTvId(tvId);
            if(tags.isEmpty() || "".equals(tags)){
                tvPlay.setTags("  ");
            }else{
                tvPlay.setTags(tags);
            }
            longVideoConsoleService.updateTvPlay(tvPlay);
            result.setMessage("设置电视剧标签完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 设置电视剧标签异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  设置长视频标签
     *
     * @param consoleToken，tvId, tags
     * @return result
     */
    @RequestMapping(value = "/console/longVideo/setLongVideoTag", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult setLongVideoTag(String consoleToken,  String videoId ,String tags) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} , videoId: {} ", traceId,  videoId );
        ResponseResult result = new ResponseResult(traceId);
        try {

            LongVideo longVideo = new LongVideo();
            longVideo.setVideoId(videoId);
            if(tags.isEmpty() || "".equals(tags)){
                longVideo.setTags("  ");
            }else{
                longVideo.setTags(tags);
            }
            longVideoConsoleService.updateLongVideo(longVideo);
            result.setMessage("设置长视频标签完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 设置长视频标签异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }



}
