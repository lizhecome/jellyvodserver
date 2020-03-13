package com.alivc.vod.dao;

import java.util.List;

import com.alivc.vod.pojo.RecommendLive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** 
 * ClassName: LiveDao <br/> 
 * Function: TODO 视频管理的dao层. <br/> 
 * Reason:   TODO 视频管理的dao层. <br/> 
 * Date:     2019年5月15日  <br/> 
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Mapper
public interface  LiveDao {
    /**
     * 获取播流列表
     * @return List<RecommendLive>
     * @param   pageIndex  pageSize
     */
    public List<RecommendLive> getRecommendLive( @Param(value="pageIndex")int pageIndex, @Param(value="pageSize")int pageSize);

}
