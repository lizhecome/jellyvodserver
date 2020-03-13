package com.alivc.vod.pojo;


/**
 * 播流信息实体类
 *
 * @author tanzhen
 * @date 2019-5-15
 */
public class RecommendLive extends BaseInfo{

    /**
     * 直播id
     */
    private String liveId = "";

    /**
     * 播流地址
     */
    private String liveUrl = "";

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getLiveUrl() {
		return liveUrl;
	}

	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}


    
    
}
