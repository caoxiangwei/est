package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 故障选项包装类
 * @Date 2019-09-03 19:30
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "MalfunctionOptionsDto", description = "故障选项包装类")
public class MalfunctionOptionsDto {

    @ApiModelProperty("故障选项id")
    private Long id;
    //故障id
    @ApiModelProperty("故障id")
    private Long malfId;
    //名称
    @ApiModelProperty("故障选项名称id")
    private String optionName;
    //是否有提示0:否,1:是)
    @ApiModelProperty("是否有提示0:否,1:是)")
    private Integer isHint;
    //提示信息
    @ApiModelProperty("提示信息")
    private String hintInfo;
    //提示图片
    @ApiModelProperty("提示图片")
    private String hintImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMalfId() {
        return malfId;
    }

    public void setMalfId(Long malfId) {
        this.malfId = malfId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getIsHint() {
        return isHint;
    }

    public void setIsHint(Integer isHint) {
        this.isHint = isHint;
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
}
