package cn.est.dto;

import cn.est.pojo.Evaluate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 评估信息包装类
 * @Date 2019-08-16 17:32
 * @Author Liujx
 * Version 1.0
 **/
@ApiModel(value = "EvaluateDto", description = "评估信息包装类")
public class EvaluateDto {

    public EvaluateDto(){}

    public EvaluateDto(Evaluate evaluate){
        this.id = evaluate.getId();
        this.modelId = evaluate.getModelId();
        this.price = evaluate.getPrice();
        this.subscription = evaluate.getSubscription();
    }

    @ApiModelProperty(value = "评估信息id")
    private Long id;
    //型号id
    @ApiModelProperty(value = "商品型号id")
    private Long modelId;
    //订金金额
    @ApiModelProperty(value = "订金金额")
    private BigDecimal subscription;
    //维修估价
    @ApiModelProperty(value = "估价")
    private BigDecimal price;
    //商品信息
    @ApiModelProperty(value = "商品信息")
    private ModelDto model;
    //评估详情信息
    @ApiModelProperty(value = "评估详情信息")
    private List<EvaluateDetailDto> detailList;

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

    public BigDecimal getSubscription() {
        return subscription;
    }

    public void setSubscription(BigDecimal subscription) {
        this.subscription = subscription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public List<EvaluateDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<EvaluateDetailDto> detailList) {
        this.detailList = detailList;
    }
}
