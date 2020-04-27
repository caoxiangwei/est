package cn.est.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description Vo包装类基类
 * 这里有一些公共的字段
 * @Date 2019-08-30 16:44
 * @Author Liujx
 * Version 1.0
 **/
public class BaseModelDto {

    // 主键id
    @ApiModelProperty(hidden = true)
    private Long id;
    // 创建人
    @ApiModelProperty(hidden = true)
    private Long createUserId;
    // 创建时间--date
    @ApiModelProperty(hidden = true)
    private Date creatdTime;
    // 创建时间--字符串
    @ApiModelProperty(hidden = true)
    private String createTimeStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreatdTime() {
        return creatdTime;
    }

    public void setCreatdTime(Date creatdTime) {
        this.creatdTime = creatdTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
