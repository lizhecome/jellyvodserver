package com.alivc.toolkit.dao;

import com.alivc.toolkit.pojo.ToolKit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToolKitDao {
    ToolKit getToolKit(ToolKit var1);
}
