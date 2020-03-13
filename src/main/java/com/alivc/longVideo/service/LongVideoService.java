package com.alivc.longVideo.service;

import com.alivc.longVideo.pojo.LongVideo;
import com.alivc.longVideo.pojo.LongVideoParm;
import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;

import java.util.List;


/** 
 * ClassName: LongVideoService <br/>
 * Function: TODO 长视频service层. <br/>
 * Reason:   TODO 用于长视频相关功能的接口. <br/>
 * Date:     2019年6月21日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
public interface LongVideoService {


	/**
	 * 根据条件获取长视频列表
	 * @return List<LongVideo>
	 * @param  longVideoParm
	 */
	public List<LongVideo> getLongVideoList(LongVideoParm longVideoParm);
	/**
	 * 根据条件获取长视频条数
	 * @return int
	 * @param  longVideoParm
	 */
	public  int selectLongVideoNums(LongVideoParm longVideoParm);
}
