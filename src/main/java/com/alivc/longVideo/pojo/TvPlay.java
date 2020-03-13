package com.alivc.longVideo.pojo;


/**
 * ClassName: TvPlay <br/>
 * Function: TODO  电视剧实体类. <br/>
 * Reason:   TODO  电视剧实体类. <br/>
 * Date:     2019年6月20日  <br/>
 * @author   tz
 * @version   v0.0.1
 * @since    JDK 1.8
 * @see
 */
public class TvPlay extends BaseInfo{

    /**
     * 是否发布
     */
    private String isRelease = "";
    /**
     * 总集数
     */
    private String total = "";

    public String getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(String isRelease) {
        this.isRelease = isRelease;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
