package com.alivc.longVideo.service.impl;

import com.alivc.base.AuthTokenGetUtil;
import com.alivc.base.ConfigMapUtil;
import com.alivc.base.JwtUtil;
import com.alivc.base.RandomString;
import com.alivc.longVideo.dao.LongVideoUserDao;
import com.alivc.longVideo.pojo.LongVideoUser;
import com.alivc.longVideo.service.LongVideoUserService;
import com.alivc.user.dao.AuthTokenDao;
import com.alivc.user.pojo.AuthToken;
import com.mysql.jdbc.StringUtils;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Random;

/** 
 * ClassName: LongVideoUserServiceImpl <br/>
 * Function: TODO 用户impl层. <br/> 
 * Reason:   TODO 用于实现用户service层的方法. <br/> 
 * Date:     2019年6月24日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see       
 */
@Service
public class LongVideoUserServiceImpl implements LongVideoUserService {
	private static final Logger LOG = LoggerFactory.getLogger(LongVideoUserServiceImpl.class);
	@Autowired
	private LongVideoUserDao longVideoUserDao;
	@Autowired
	private AuthTokenDao tokenDao;


	/**
     * 生成随机用户
     * 
     * @param param
     *            查询参数
     * @return result
     */
	@Override
	public LongVideoUser randomUser() {
		Random rand = new Random();
		LongVideoUser guestUser = new LongVideoUser();
		// 配置随机用户参数
		LongVideoUser user = new LongVideoUser();
		user.setUserId(RandomString.getData());
		user.setNickName(RandomString.getRandomName());
		Random random = new Random();
		String str = ConfigMapUtil.getValueByKey("AVATAR_URL");
        //截取获得字符串数组
        String[] urlArray = str.split(",");
        int randomSize=random.nextInt(urlArray.length);
		user.setAvatarUrl(urlArray[randomSize]);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(System.currentTimeMillis());
		user.setGmtCreate(time);
		try {
			longVideoUserDao.insertGuest(user);
			//生成token
			String userId = user.getUserId();
            JSONObject json = new JSONObject();
            json.put("useId", userId);
            String subject = json.toJSONString();
            String token = JwtUtil.createJWT(userId, subject, (long) (30 * 24 * 60 * 60 * 1000));
            //检查token重复
            AuthToken istoken = tokenDao.selectByPrimaryKey(token);
            while (istoken != null) {
                token = JwtUtil.createJWT(userId, subject, (long) (30 * 24 * 60 * 60 * 1000));
                istoken = tokenDao.selectByPrimaryKey(token);
            }
			//插入数据库token
            AuthToken tokenInsert = new AuthToken();
            tokenInsert.setKey(token);
            tokenInsert.setCreatetime(format.format(System.currentTimeMillis()));
            tokenInsert.setUserId(userId);
            tokenDao.insert(tokenInsert);
            guestUser = longVideoUserDao.getUserById(user);
			guestUser.setAvatarUrl(ConfigMapUtil.getValueByKey("AVATAR_DOMAIN_NAME") + guestUser.getAvatarUrl());
			guestUser.setToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("---生成长视频随机用户异常", e);
		}
		return guestUser;
	}


    @Override
    public LongVideoUser getUserById(String userId) {
		LongVideoUser user = new LongVideoUser();
        user.setUserId(userId);
        try {
            user = longVideoUserDao.getUserById(user);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("---获取用户信息异常", e);
        }
        return user;
    }

	@Override
	public boolean getUserByToken(String token) {
		boolean isUser = true;
		try {
			if(StringUtils.isNullOrEmpty(token)){
				isUser = false;
				LOG.error("token不能为空");
			}else{
				String userId = AuthTokenGetUtil.getUserIdFormToken(token);
				LongVideoUser user = new LongVideoUser();
				user.setUserId(userId);
				if(userId != null && !"".equals(userId)){
					LongVideoUser userResult = longVideoUserDao.getUserById(user);
					if(userResult != null){
						isUser = true;
						LOG.info("获取用户成功");
					}else{
						isUser = false;
						LOG.info("该token无法查到用户");
					}
				}else{
					isUser = false;
					LOG.error("无法查到userId");
				}

			}} catch (Exception e) {
			e.printStackTrace();
			LOG.error("---判断token异常", e);
		}
		return  isUser;

	}

}
