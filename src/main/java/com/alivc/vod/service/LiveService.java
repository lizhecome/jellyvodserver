package com.alivc.vod.service;

import java.util.List;

import com.alivc.vod.pojo.RecommendLive;

/** 
 * ClassName: LiveService <br/> 
 * Function: TODO 播流service层. <br/> 
 * Reason:   TODO 用于播流相关功能的接口. <br/> 
 * Date:     2019年5月15日  <br/> 
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
public interface LiveService {
	
	 /**
	 * 获取推荐播流列表
	 * @param 
	 * @return List<RecommendLive>
	 */
	public List<RecommendLive> getRecommendLive(int pageIndex, int pageSize);
	 
}
