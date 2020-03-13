package com.alivc.longVideo.service;

import com.alivc.base.ResponseResult;
import com.alivc.longVideo.pojo.LongVideoUser;

/** 
 * ClassName: LongVideoUserService <br/>
 * Function: TODO 长视频用户service层. <br/>
 * Reason:   TODO 长视频用于用户相关功能的接口. <br/>
 * Date:     2019年6月24日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
public interface LongVideoUserService {

	/**
	 * 新增随机用户
	 * @return ResponseResult
	 */
	public LongVideoUser randomUser();

	/**
     * 根据用户id获取用户信息
     * @param userId
     * @return User
     */
    public LongVideoUser getUserById(String userId);

	/**
     * 根据token查询用户
     * @param token
     * @return boolean
     */
	public boolean getUserByToken(String token);


}
