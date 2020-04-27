package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description 故障包装类
 * @Date 2019-08-16 16:15
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "MalfunctionDto", description = "故障包装类")
public class MalfunctionDto {

    @ApiModelProperty("故障id")
    private Long id;
    //商品型号id
    @ApiModelProperty("商品型号id")
    private Long modelId;
    //故障状况名称
    @ApiModelProperty("故障状况名称")
    private String title;
    //是否有提示0:否,1:是)
    @ApiModelProperty("是否有提示(0:否,1:是)")
    private Integer isHint;
    //提示信息标题
    @ApiModelProperty("提示信息标题")
    private String hintTitle;
    //提示信息
    @ApiModelProperty("提示信息")
    private String hintInfo;
    //提示图片
    @ApiModelProperty("提示图片")
    private String hintImg;

    //提示图片
    @ApiModelProperty("故障详情")
    private List<MalfunctionOptionsDto> optionsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsHint() {
        return isHint;
    }

    public void setIsHint(Integer isHint) {
        this.isHint = isHint;
    }

    public String getHintTitle() {
        return hintTitle;
    }

    public void setHintTitle(String hintTitle) {
        this.hintTitle = hintTitle;
    }

    public String getHintInfo() {
        return hintInfo;
    }

    public void setHintInfo(String hintInfo) {
        this.hintInfo = hintInfo;
    }

    public String getHintImg() {
        return hintImg;
    }

    public void setHintImg(String hintImg) {
        this.hintImg = hintImg;
    }

    public List<MalfunctionOptionsDto> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<MalfunctionOptionsDto> optionsList) {
        this.optionsList = optionsList;
    }
}
