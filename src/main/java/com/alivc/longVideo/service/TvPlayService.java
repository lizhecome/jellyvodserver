package com.alivc.longVideo.service;

import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;

import java.util.List;


/** 
 * ClassName: TvPlayService <br/>
 * Function: TODO 电视剧service层. <br/>
 * Reason:   TODO 用于电视剧相关功能的接口. <br/>
 * Date:     2019年6月21日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
public interface TvPlayService {


	/**
	 * 根据条件获取电视剧列表
	 * @return List<TvPlay>
	 * @param  tvPlayParm
	 */
	public List<TvPlay> getTvPlayList(TvPlayParm tvPlayParm);
	/**
	 * 根据条件获取电视剧条数
	 * @return int
	 * @param  tvPlayParm
	 */
	public  int selectTvPlayNums(TvPlayParm tvPlayParm);

}
