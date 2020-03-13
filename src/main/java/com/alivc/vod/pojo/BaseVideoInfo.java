package com.alivc.vod.pojo;


/**
 * 视频信息实体类
 *
 * @author haihua.whh
 * @date 2018-12-29
 */
public class BaseVideoInfo extends BaseInfo{

    /**
     * 视频id
     */
    private String videoId = "";
    /**
     * 视频时长（秒）
     */
    private float duration = 0f;

    /**
     * 转码状态
     */
    private String transcodeStatus = "";
    /**
     * 截图状态
     */
    private String snapshotStatus = "";
    /**
     * 审核状态
     */
    private String censorStatus = "";
    /**
     * 窄带转码状态
     */
    private String narrowTranscodeStatus = "";
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public String getTranscodeStatus() {
		return transcodeStatus;
	}
	public void setTranscodeStatus(String transcodeStatus) {
		this.transcodeStatus = transcodeStatus;
	}
	public String getSnapshotStatus() {
		return snapshotStatus;
	}
	public void setSnapshotStatus(String snapshotStatus) {
		this.snapshotStatus = snapshotStatus;
	}
	public String getCensorStatus() {
		return censorStatus;
	}
	public void setCensorStatus(String censorStatus) {
		this.censorStatus = censorStatus;
	}
	public String getNarrowTranscodeStatus() {
		return narrowTranscodeStatus;
	}
	public void setNarrowTranscodeStatus(String narrowTranscodeStatus) {
		this.narrowTranscodeStatus = narrowTranscodeStatus;
	}

    
}
