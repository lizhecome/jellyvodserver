package com.alivc.longVideo.dao;

import com.alivc.longVideo.pojo.LongVideo;
import com.alivc.longVideo.pojo.LongVideoParm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * ClassName: LongVideoDao <br/>
 * Function: TODO 长视频管理的dao层. <br/>
 * Reason:   TODO 长视频管理的dao层. <br/>
 * Date:     2019年6月21日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface LongVideoDao {


	/**
	 * 根据条件获取长视频列表
	 * @return List<LongVideo>
	 * @param    longVideoParm
	 */
	public List<LongVideo> getLongVideoList(LongVideoParm longVideoParm);
	/**
	 * 根据条件获取长视频条数
	 * @return int
	 * @param  longVideoParm
	 */
	public  int selectLongVideoNums(LongVideoParm longVideoParm);


}
