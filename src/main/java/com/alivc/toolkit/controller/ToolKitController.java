package com.alivc.toolkit.controller;

import com.alivc.base.ResponseResult;
import com.alivc.base.TraceIdContext;
import com.alivc.toolkit.pojo.ToolKit;
import com.alivc.toolkit.service.ToolKitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class ToolKitController {

    @Resource
    private ToolKitService toolKitService;

    @ResponseBody
    @GetMapping("/tool/getToolKit")
    public ResponseResult getToolKit(String toolKitName, String type) {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        ResponseResult result = new ResponseResult(traceId);
        ToolKit toolKitParm = new ToolKit();
        toolKitParm.setToolKitName(toolKitName);
        toolKitParm.setType(type);

        try {
            ToolKit toolKit = this.toolKitService.getToolKit(toolKitParm);
            result.setData(toolKit);
            result.setMessage("根据条件获取工具包信息完成");
        } catch (Exception var7) {
            result.setResult("false");
            result.setCode("500");
            result.setMessage(var7.getMessage());
            result.setData(null);
            log.error("--- 根据条件获取工具包信息异常", var7);
        }

        return result;
    }
}
