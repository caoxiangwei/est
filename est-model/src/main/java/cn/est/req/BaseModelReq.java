package cn.est.req;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.util.Date;

/**
 * @Description Vo包装类基类
 * 这里有一些公共的字段
 * @Date 2019-08-30 16:44
 * @Author Liujx
 * Version 1.0
 **/
public class BaseModelReq {

    // 主键id
    @ApiParam(hidden = true)
    private Long id;
    // 创建人
    @ApiParam(hidden = true)
    private Long createUserId;
    // 创建时间--date
    @ApiModelProperty(hidden = true)
    private Date creatdTime;
    // 创建时间--字符串
    @ApiModelProperty(hidden = true)
    private String createTimeStr;
    //是否删除(0:否,1:是)
    @ApiModelProperty(hidden = true)
    private Integer isDelete;
    @ApiModelProperty(value = "页码")
    private Integer pageNo;
    @ApiModelProperty(value = "页长")
    private Integer pageSize;


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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            pageSize = 20;
        }
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo == null){
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

}
