package cn.est.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 评估信息详情
 * @Date 2019-09-03 18:39
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(description = "评估信息详情")
public class EvaluateDetailDto {

    @ApiModelProperty("评估信息详情id")
    private Long id;
    //评估信息Id
    @ApiModelProperty("评估信息Id")
    private Long evaluateId;
    //故障id
    @ApiModelProperty("故障id")
    private Long malfId;
    //故障选项选项id
    @ApiModelProperty("故障选项选项id")
    private Long optionId;
    //故障选项选项名称
    private String optionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Long getMalfId() {
        return malfId;
    }

    public void setMalfId(Long malfId) {
        this.malfId = malfId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
