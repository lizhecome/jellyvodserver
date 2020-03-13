package com.alivc.longVideo.dao;

import com.alivc.longVideo.pojo.Dot;
import com.alivc.longVideo.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * ClassName: TagDao <br/>
 * Function: TODO 打点信息管理的dao层. <br/>
 * Reason:   TODO 打点信息管理的dao层. <br/>
 * Date:     2019年7月12日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface DotDao {

	/**
	 * 根据videoId查询dot列表
	 * @return List<Dot>
	 * @param    videoId
	 */
	public List<Dot> getTotListById(@Param(value="videoId")String videoId);
	/**
	 * 添加打点信息
	 * @param dot
	 * @return  int
	 */
	public 	int insertDot(Dot dot);
	/**
	 * 删除打点信息
	 * @return
	 * @param  videoId
	 */
	public  void deleteDotById(@Param(value="videoId")String videoId);



    

}
