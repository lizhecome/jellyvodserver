package com.alivc.longVideo.service.impl;

import com.alivc.longVideo.dao.TvPlayDao;
import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;
import com.alivc.longVideo.service.TvPlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 
 * ClassName: TvPlayServiceImpl <br/>
 * Function: TODO 电视剧impl层. <br/>
 * Reason:   TODO 用于实现电视剧service层的方法. <br/>
 * Date:     2019年6月21日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Service
public class TvPlayServiceImpl implements TvPlayService {
	private static final Logger LOG = LoggerFactory.getLogger(TvPlayServiceImpl.class);
	@Autowired
	private TvPlayDao tvPlayDao;
	

    /**
     * 根据条件获取电视剧列表
     *
     * @param
     *             tvPlayParm
     * @return List<TvPlay>
     */
    @Override
    public List<TvPlay> getTvPlayList(TvPlayParm tvPlayParm) {
        List<TvPlay> tvPlayList = new ArrayList<TvPlay>();
        try {
            tvPlayList = tvPlayDao.getTvPlayList(tvPlayParm);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---根据条件获取电视剧列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return tvPlayList;
    }

    @Override
    public int selectTvPlayNums(TvPlayParm tvPlayParm) {
        int count = 0 ;
        try {
            count = tvPlayDao.selectTvPlayNums(tvPlayParm);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---获取电视剧总条数异常", e);
        }
        return count;
    }

}
