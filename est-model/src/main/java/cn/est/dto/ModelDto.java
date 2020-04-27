package cn.est.dto;

import cn.est.pojo.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 商品型号包装类
 * @Date 2019-08-15 11:16
 * @Author Liujx
 * Version 1.0
 **/

@ApiModel(value = "ModelDto", description = "商品型号包装类")
public class ModelDto{

    public ModelDto(){}

    public ModelDto(Model model){
        this.id = model.getId();
        this.brandId = model.getBrandId();
        this.classifyId = model.getClassifyId();
        this.modelName = model.getModelName();
        this.faceImg = model.getFaceImg();
        this.contentImg = model.getContentImg();
    }

    // 主键
    @ApiModelProperty("商品型号id")
    private Long id;
    //型号名称
    @ApiModelProperty("商品型号名称")
    private String modelName;
    //品牌id
    @ApiModelProperty("品牌id")
    private Long brandId;
    //类型id
    @ApiModelProperty("类型id")
    private Long classifyId;
    //当前行情价格
//    @ApiModelProperty("当前行情价格")
//    private BigDecimal exchangePrice;
    //最高价
//    @ApiModelProperty("最高价")
//    private BigDecimal topPrice;
    //封面图片
    @ApiModelProperty("封面图片")
    private String faceImg;
    //内容图片
    @ApiModelProperty("内容图片")
    private String contentImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }
}
