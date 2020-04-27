package cn.est.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @Description 商品型号入参包装类
 * @Date 2019-08-15 11:16
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "ModelReq", description = "商品型号入参包装类")
public class ModelReq{

    private ModelReq(){}

    public ModelReq(Long classifyId, Long brandId, String modelName, Integer beginPos, Integer pageSize){
        this.brandId = brandId;
        this.classifyId = classifyId;
        this.modelName = modelName;
        this.setPageSize(pageSize);
    }

    //型号名称
    @ApiParam("商品型号名称")
    private String modelName;
    //品牌id
    @ApiParam(value = "【必填】品牌id")
    private Long brandId;
    //类型id
    @ApiParam("类型id")
    private Long classifyId;
    // 页码
    @ApiParam(value = "页码", defaultValue = "1")
    private Integer pageNo;
    // 页长
    @ApiParam(value = "页长", defaultValue = "20")
    private Integer pageSize;

    // 数据库分页查询开始
    @ApiParam(hidden = true)
    private Integer beginPos;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
