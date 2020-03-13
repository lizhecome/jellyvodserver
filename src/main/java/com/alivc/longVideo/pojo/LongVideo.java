package com.alivc.longVideo.pojo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LongVideo <br/>
 * Function: TODO 长视频实体类. <br/>
 * Reason:   TODO 长视频实体类. <br/>
 * Date:     2019年6月19日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see 
 */
public class LongVideo extends BaseInfo {


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
	 * 截图地址list
	 */
	private List<String> snapshotList;
	/**
	 * 打点信息列表
	 */
	List<Dot> dotList = new ArrayList<Dot>();
	/**
	 * 序号
	 */
	private String sort;
	/**
	 * 是否为vip视频
	 */
	private String isVip;

	public List<Dot> getDotList() {
		return dotList;
	}

	public void setDotList(List<Dot> dotList) {
		this.dotList = dotList;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<String> getSnapshotList() {
		return snapshotList;
	}
	public void setSnapshotList(List<String> snapshotList) {
		this.snapshotList = snapshotList;
	}

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
}
