package com.alivc.longVideo.service.impl;

import com.alivc.longVideo.dao.LongVideoDao;
import com.alivc.longVideo.pojo.LongVideo;
import com.alivc.longVideo.pojo.LongVideoParm;
import com.alivc.longVideo.service.LongVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 
 * ClassName: LongVideoServiceImpl <br/>
 * Function: TODO 长视频impl层. <br/>
 * Reason:   TODO 用于实现长视频service层的方法. <br/>
 * Date:     2019年6月21日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Service
public class LongVideoServiceImpl implements LongVideoService {
	private static final Logger LOG = LoggerFactory.getLogger(LongVideoServiceImpl.class);
	@Autowired
	private LongVideoDao longVideoDao;
	



    /**
     * 首页电视剧列表
     *
     * @param
     *             longVideParm
     * @return List<TvPlay>
     */
    @Override
    public List<LongVideo> getLongVideoList(LongVideoParm longVideParm) {
        List<LongVideo> longVideoList = new ArrayList<LongVideo>();
        try {
            longVideoList = longVideoDao.getLongVideoList(longVideParm);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---根据条件获取长视频列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return longVideoList;
    }

    @Override
    public int selectLongVideoNums(LongVideoParm longVideoParm) {
        int count = 0 ;
        try {
            count = longVideoDao.selectLongVideoNums(longVideoParm);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---获取长视频总条数异常", e);
        }
        return count;
    }


}
