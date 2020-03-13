package com.alivc.longVideo.controller;


import com.alivc.base.ConstanData;
import com.alivc.base.ResponseResult;
import com.alivc.base.TraceIdContext;
import com.alivc.longVideo.pojo.LongVideoUser;
import com.alivc.longVideo.service.LongVideoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.UnknownHostException;

/**
 * ClassName: LongVideoUserController <br/>
 * Function: TODO 长视频用户管理控制器. <br/>
 * Reason: TODO 长视频用户管理. <br/>
 * Date: 2019年6月24日 <br/>
 * 
 * @author tz
 * @version v0.0.1
 * @since JDK 1.8
 * @see
 */
@RestController
public class LongVideoUserController {

    private static final Logger LOG = LoggerFactory.getLogger(LongVideoUserController.class);
    @Autowired
    private LongVideoUserService longVideoUserService;

    /**
     * 生成长视频随机用户,并返回用户信息
     * 
     * @param   
     * @return result
     * @throws UnknownHostException 
     */
    @RequestMapping(value = "/longVideoUser/randomUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult randomUser() throws UnknownHostException {
        String traceId = TraceIdContext.ctx.get().getTraceId();
        LOG.info("traceId: {} ", traceId);
        ResponseResult result = new ResponseResult(traceId);
        try {
            LongVideoUser guestUser = new LongVideoUser();
            guestUser = longVideoUserService.randomUser();
            result.setData(guestUser);
            result.setMessage("生成长视频随机用户完成");
        } catch (Exception e) {
            result.setResult("false");
            result.setCode(ConstanData.INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
            result.setData(null);
            LOG.error("--- 生成长视频随机用户异常", e);
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

}
