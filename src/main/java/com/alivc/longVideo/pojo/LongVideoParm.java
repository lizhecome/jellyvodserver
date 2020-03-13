package com.alivc.longVideo.pojo;

/**
 * 长视频参数实体类
 *
 * @author tanzhen
 * @date 2019-6-19
 */
public class LongVideoParm {

    /**
     * id
     */
    private String id = "";
    /**
     * tvId
     */
    private String tvId = "";
    /**
     * videoId
     */
    private String videoId = "";

    /**
     * 标题
     */
    private String title = "";
    /**
     * 描述
     */
    private String description = "";

    /**
     * 大小
     */
    private int size = 0;
    /**
     * 分类id
     */
    private int cateId = 0;
    /**
     * 分类名称
     */
    private String cateName = "";
    /**
     * 序号
     */
    private String sort = "";
    /**
     * 修改序号
     */
    private String updateSort = "";
    /**
     * 标签
     */
    private String tags = "";
    /**
     * 审核状态
     */
    private String censorStatus = "";
    /**
     * 是否推荐
     */
    private String isRecommend = "";
    /**
     * 是否首页
     */
    private String isHomePage = "";
    /**
     * 是否发布
     */
    private String isRelease = "";
    /**
     * 是否vip
     */
    private String isVip = "";
    /**
     * 起始页
     */
    private int pageIndex = 0;
    /**
     *  页码
     */
    private int pageSize = 100;
    /**
     * 开始时间
     */
    private String startTime = "";
    /**
     * 结束时间
     */
    private String endTime = "";

    private Boolean onlyVideo;

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCensorStatus() {
        return censorStatus;
    }

    public void setCensorStatus(String censorStatus) {
        this.censorStatus = censorStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(String isRelease) {
        this.isRelease = isRelease;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIsHomePage() {
        return isHomePage;
    }

    public void setIsHomePage(String isHomePage) {
        this.isHomePage = isHomePage;
    }

    public String getTvId() {
        return tvId;
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUpdateSort() {
        return updateSort;
    }

    public void setUpdateSort(String updateSort) {
        this.updateSort = updateSort;
    }

    public Boolean getOnlyVideo() {
        return onlyVideo;
    }

    public void setOnlyVideo(Boolean onlyVideo) {
        this.onlyVideo = onlyVideo;
    }
}
