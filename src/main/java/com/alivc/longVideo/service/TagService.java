package com.alivc.longVideo.service;

import com.alivc.longVideo.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/** 
 * ClassName: TagService <br/>
 * Function: TODO 标签service层. <br/>
 * Reason:   TODO 用于标签相关功能的接口. <br/>
 * Date:     2019年7月2日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
public interface TagService {


	/**
	 * 根据类型获取标签列表
	 * @return List<Tag>
	 * @param  type
	 */
	public List<Tag> getTagsListByType(String type);
	/**
	 * 添加标签
	 * @param tag
	 * @return  int
	 */
	public 	int insertTag(Tag tag);
	/**
	 * 获取标签列表
	 * @return List<Tag>
	 * @param    type tagName
	 */
	public List<Tag> getTagsList(@Param(value="type")String type, @Param(value="tagName")String tagName);

	/**
	 * 删除标签信息
	 * @return
	 * @param  tagId
	 */
	public  void deleteTagById(@Param(value="tagId")String tagId);
	/**
	 * 根据tag获取标签信息
	 * @return Tag
	 * @param  tagId
	 *//*
	public Tag getTagByType(String tagId);*/

}
