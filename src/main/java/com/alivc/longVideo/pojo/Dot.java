package com.alivc.longVideo.pojo;


/**
 * ClassName: Tag <br/>
 * Function: TODO  打点信息实体类. <br/>
 * Reason:   TODO  打点信息实体类. <br/>
 * Date:     2019年7月12日  <br/>
 * @author   tz
 * @version   v0.0.1
 * @since    JDK 1.8
 * @see
 */
public class Dot {

    /**
     * id
     */
    private String id = "";
    /**
     * 视频id
     */
    private String videoId = "";
    /**
     * 秒数
     */
    private String time = "";
    /**
     * 打点内容
     */
    private String content = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
