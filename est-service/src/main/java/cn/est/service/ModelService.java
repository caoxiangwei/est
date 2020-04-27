package cn.est.service;

import cn.est.dto.Page;
import cn.est.dto.ModelDto;
import cn.est.req.ModelReq;

import java.util.List;

/**
 * @Description 商品模型业务层接口
 * @Date 2019-08-15 15:03
 * @Author Liujx
 * Version 1.0
 **/
public interface ModelService {

    /**
     * 分页查询商品模型数据
     * @param modelReq
     * @return
     */
    Page<ModelDto> getModelPage(ModelReq modelReq);

    /**
     * 根据id查询商品模型详情
     * @param id
     * @return
     */
    ModelDto getById(Long id);

    /**
     * 根据vo对象查询商品类型列表
     * @param modelReq
     * @return
     */
    List<ModelDto> getModelList(ModelReq modelReq);

}
