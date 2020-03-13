package com.alivc.longVideo.dao;

import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * ClassName: TvPlayDao <br/>
 * Function: TODO 电视剧管理的dao层. <br/>
 * Reason:   TODO 电视剧管理的dao层. <br/>
 * Date:     2019年6月20日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface TvPlayDao {


	/**
	 * 根据条件获取电视剧列表
	 * @return List<TvPlay>
	 * @param    tvPlayParm
	 */
	public List<TvPlay> getTvPlayList(TvPlayParm tvPlayParm);

	/**
	 * 根据条件获取电视剧条数
	 * @return int
	 * @param  tvPlayParm
	 */
	public  int selectTvPlayNums(TvPlayParm tvPlayParm);


    

}
