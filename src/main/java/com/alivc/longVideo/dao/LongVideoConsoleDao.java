package com.alivc.longVideo.dao;

import com.alivc.longVideo.pojo.LongVideo;
import com.alivc.longVideo.pojo.LongVideoParm;
import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/** 
 * ClassName: LongVideoUserDao <br/>
 * Function: TODO 长视频控制台的dao层. <br/>
 * Reason:   TODO 长视频控制台的dao层. <br/>
 * Date:     2019年6月25日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface LongVideoConsoleDao {
	
    /**
     * 插入长视频
     * @param longVideo
     * @return  int
     */
	public 	int insertLongVideo(LongVideo longVideo);
	/**
	 * 更新长视频
	 * @param longVideo
	 * @return int
	 */
	public int updateLongVideo(LongVideo longVideo);
	/**
	 * 新建电视剧
	 * @param tvPlay
	 * @return  int
	 */
	public 	int insertTvPlay(TvPlay tvPlay);
	/**
	 * 根据电视剧id删除电视剧
	 * @return
	 * @param  tvId
	 */
	public  void deleteTvPlayById(@Param(value="tvId")String tvId);
	/**
	 * 根据长视频id删除长视频
	 * @return
	 * @param  videoId
	 */
	public  void deleteLongVideoById(@Param(value="videoId")String videoId);
	/**
	 * 更新电视剧
	 * @param tvPlay

	 * @return int
	 */
	public int updateTvPlay(TvPlay tvPlay);
	/**
	 * 修改大于某序号的长视频序号
	 * @param longVideo
	 * @return int
	 */
	public int updateLongVideoSort(LongVideo longVideo);
	/**
	 * 修改电视剧总集数 （规则：在原本的基础上加1）
	 * @param tvId
	 * @return int
	 */
	public int updateTvPlayTotal(String tvId);
	/**
	 * 对某区间长视频序号修改（规则：在原本的基础上减1）
	 * @param longVideoParm
	 * @return int
	 */
	public int subtractLongVideoSort(LongVideoParm longVideoParm);
	/**
	 * 对某区间长视频序号修改（规则：在原本的基础上加1）
	 * @param longVideoParm
	 * @return int
	 */
	public int addLongVideoSort(LongVideoParm longVideoParm);





}
