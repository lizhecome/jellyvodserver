package com.alivc.longVideo.dao;

import com.alivc.longVideo.pojo.LongVideoUser;
import com.alivc.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;


/** 
 * ClassName: LongVideoUserDao <br/>
 * Function: TODO 长视频用户的dao层. <br/>
 * Reason:   TODO 长视频用户的dao层. <br/>
 * Date:     2019年6月24日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface LongVideoUserDao {
	
    /**
     * 根据id获取用户信息
     * @param user
     * @return  User
     */
	public 	LongVideoUser getUserById(LongVideoUser user);
	/**
	 * 插入游客信息
	 * @param user
	 */
	public 	void insertGuest(LongVideoUser user);

}
