package com.alivc.toolkit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alivc.toolkit.dao.ToolKitDao;
import com.alivc.toolkit.pojo.ToolKit;
import com.alivc.toolkit.service.ToolKitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class ToolKitServiceImpl implements ToolKitService {
    @Resource
    private ToolKitDao toolKitDao;

    @Override
    public ToolKit getToolKit(ToolKit toolKitPram) {
        ToolKit toolKit = this.toolKitDao.getToolKit(toolKitPram);
        log.debug(JSON.toJSONString(toolKit));
        return toolKit;
    }
}
