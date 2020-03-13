package com.alivc.longVideo.service.impl;

import com.alivc.base.RandomString;
import com.alivc.longVideo.dao.TagDao;
import com.alivc.longVideo.dao.TvPlayDao;
import com.alivc.longVideo.pojo.Tag;
import com.alivc.longVideo.pojo.TvPlay;
import com.alivc.longVideo.pojo.TvPlayParm;
import com.alivc.longVideo.service.TagService;
import com.alivc.longVideo.service.TvPlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 
 * ClassName: TvPlayServiceImpl <br/>
 * Function: TODO 标签impl层. <br/>
 * Reason:   TODO 用于实现标签service层的方法. <br/>
 * Date:     2019年6月21日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Service
public class TagServiceImpl implements TagService {
	private static final Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);
	@Autowired
	private TagDao tagDao;
	

    /**
     * 根据类型获取标签列表
     *
     * @param
     *             type
     * @return List<Tag>
     */
    @Override
    public List<Tag> getTagsListByType(String type) {
        List<Tag> tagList = new ArrayList<Tag>();
        try {
            tagList = tagDao.getTagsListByType(type);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---根据类型获取标签列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return tagList;
    }

    @Override
    public int insertTag(Tag tag) {
        int insert = 0;
        try {
            insert = tagDao.insertTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---新建标签异常", e);
        }
        return insert;

    }

    @Override
    public List<Tag> getTagsList(String type, String tagName) {
        List<Tag> tagList = new ArrayList<Tag>();
        try {
            tagList = tagDao.getTagsList(type,tagName);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("--- 获取标签列表异常", e);
            LOG.error(e.getMessage(), e);
        }
        return tagList;
    }

    @Override
    public void deleteTagById(String tagId) {
        try {
            tagDao.deleteTagById(tagId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---删除标签信息异常", e);
        }

    }

}
