package com.alivc.longVideo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
 * ClassName: LongUser <br/>
 * Function: TODO 长视频用户实体类. <br/>
 * Reason:   TODO 长视频用户实体类. <br/>
 * Date:     2019年6月24日  <br/>
 * @author   tz 
 * @version   v0.0.1
 * @since    JDK 1.8 
 * @see
 */
public class LongVideoUser {
    /**
     *  id
     */          
	  private String id = "";
	  /**
       * 用户id
       */
	  private String userId = "";
	  /**
	   * 用户昵称
	   */
	  private String nickName = "";
	  /**
       * 用户头像
       */
	  private String avatarUrl = "";
	  /**
       * 创建时间
       */
	  private String gmtCreate = "";
	  /**
       *修改时间 
       */
	  private String gmtModified = "";
	  /**
	   * token
	   */
	  private String token = "";
	  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
	  
	  
	  

}
