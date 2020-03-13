package com.alivc.longVideo.service.impl;

import com.alivc.longVideo.dao.DotDao;
import com.alivc.longVideo.dao.TagDao;
import com.alivc.longVideo.pojo.Dot;
import com.alivc.longVideo.pojo.Tag;
import com.alivc.longVideo.service.DotService;
import com.alivc.longVideo.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 
 * ClassName: TvPlayServiceImpl <br/>
 * Function: TODO 打点信息impl层. <br/>
 * Reason:   TODO 用于实现打点信息service层的方法. <br/>
 * Date:     2019年7月12日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Service
public class DotServiceImpl implements DotService {
	private static final Logger LOG = LoggerFactory.getLogger(DotServiceImpl.class);
	@Autowired
	private DotDao dotDao;

    @Override
    public List<Dot> getTotListById(String videoId) {
        List<Dot> dotList = new ArrayList<Dot>();
        try {
            dotList = dotDao.getTotListById(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---根据视频id获取打点列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return dotList;
    }


    @Override
    public int insertDot(Dot dot) {
        int insert = 0;
        try {
            insert = dotDao.insertDot(dot);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---新建打点信息异常", e);
        }
        return insert;

    }

    @Override
    public void deleteDotById(String videoId) {
        try {
            dotDao.deleteDotById(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---删除打点信息异常", e);
        }

    }
}
