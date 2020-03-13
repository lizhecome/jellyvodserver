package com.alivc.longVideo.pojo;


/**
 * ClassName: Tag <br/>
 * Function: TODO  标签实体类. <br/>
 * Reason:   TODO  标签剧实体类. <br/>
 * Date:     2019年7月2日  <br/>
 * @author   tz
 * @version   v0.0.1
 * @since    JDK 1.8
 * @see
 */
public class Tag  {

    /**
     * id
     */
    private String id = "";
    /**
     * 标签id
     */
    private String tagId = "";
    /**
     * 标签名
     */
    private String tagName = "";
    /**
     * 标签类型
     */
    private String type = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
