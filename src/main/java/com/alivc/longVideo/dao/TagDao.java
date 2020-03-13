package com.alivc.longVideo.dao;

import com.alivc.longVideo.pojo.Tag;
import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 
 * ClassName: TagDao <br/>
 * Function: TODO 标签管理的dao层. <br/>
 * Reason:   TODO 标签管理的dao层. <br/>
 * Date:     2019年7月2日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface TagDao {

	/**
	 * 根据类型获取标签列表
	 * @return List<Tag>
	 * @param    type
	 */
	public List<Tag> getTagsListByType(String type);
	/**
	 * 获取标签列表
	 * @return List<Tag>
	 * @param    type tagName
	 */
	public List<Tag> getTagsList(@Param(value="type")String type, @Param(value="tagName")String tagName);
	/**
	 * 添加标签
	 * @param tag
	 * @return  int
	 */
	public 	int insertTag(Tag tag);
	/**
	 * 删除标签信息
	 * @return
	 * @param  tagId
	 */
	public  void deleteTagById(@Param(value="tagId")String tagId);


}
