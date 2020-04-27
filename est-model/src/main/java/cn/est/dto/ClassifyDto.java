package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description 商品分类包装类
 * @Date 2019-08-15 10:33
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value ="ClassifyDto", description = "商品分类包装类")
public class ClassifyDto {

    @ApiModelProperty("分类id")
    private Long id;
    // 类型名称
    @ApiModelProperty("分类名称")
    private String classifyName;
    // 图标
    @ApiModelProperty("分类图标")
    private String icon;
    // 大图标
    @ApiModelProperty("分类大图标")
    private String bigIcon;
    // 品牌数组数据
    @ApiModelProperty("品牌数组数据")
    private List<BrandDto> brandList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public List<BrandDto> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandDto> brandList) {
        this.brandList = brandList;
    }
}
