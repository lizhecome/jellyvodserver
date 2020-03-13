package com.alivc.longVideo.pojo;

/**
 * 公共实体类
 *
 * @author tanzhen
 * @date 2019-6-19
 */
public class BaseInfo {

    /**
     * id
     */
    private String id = "";
    /**
     * tvId
     */
    private String tvId = "";
	/**
	 * tvName
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
     * 封面url
     */
    private String coverUrl = "";
    /**
     * 创建时间
     */
    private String creationTime = "";

    /**
     * 首帧地址
     */
    private String firstFrameUrl = "";
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

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getFirstFrameUrl() {
		return firstFrameUrl;
	}

	public void setFirstFrameUrl(String firstFrameUrl) {
		this.firstFrameUrl = firstFrameUrl;
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

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getTvId() {
		return tvId;
	}

	public void setTvId(String tvId) {
		this.tvId = tvId;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}
}
