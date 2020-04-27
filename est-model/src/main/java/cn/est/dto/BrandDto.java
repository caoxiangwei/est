package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description 品牌包装类
 * @Date 2019-08-15 10:35
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "BrandDto", description = "品牌包装类")
public class BrandDto {

    @ApiModelProperty("品牌id")
    private Long id;
    // 品牌名称
    @ApiModelProperty("品牌名称")
    private String brandName;
    // 品牌logo
    @ApiModelProperty("品牌logo")
    private String logo;
    // 商品数组数据
    @ApiModelProperty("商品数组数据")
    private List<ModelDto> modelList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<ModelDto> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelDto> modelList) {
        this.modelList = modelList;
    }
}
