package com.alivc.longVideo.pojo;

/**
 * 电视剧参数实体类
 *
 * @author tanzhen
 * @date 2019-6-21
 */
public class TvPlayParm {

    /**
     * id
     */
    private String id = "";
    /**
     * tvId
     */
    private String tvId = "";
	/**
	 * 电视剧名称
	 */
	private String tvName = "";

    /**
     * 标题
     */
    private String title = "";
    /**
     * 描述
     */
    private String description = "";
    /**
     * 总集数
     */
    private String total = "0";
    
    /**
     * 分类名称
     */
    private String cateName = "";
    /**
     * 标签
     */
    private String tags = "";
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
	 * 起始页
	 */
	private int pageIndex ;
	/**
	 *  页码
	 */
	private int pageSize ;
	/**
	 * 开始时间
	 */
	private String startTime = "";
	/**
	 * 结束时间
	 */
	private String endTime = "";
	/**
	 * 源文件名称
	 */
	private String fileName = "";
	/**
	 * 封面地址
	 */
	private String coverUrl = "";

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
}
