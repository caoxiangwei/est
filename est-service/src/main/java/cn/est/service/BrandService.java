package cn.est.service;

import cn.est.dto.BrandDto;

import java.util.List;

/**
 * @Description 品牌信息业务层接口
 * @Date 2019-08-20 19:50
 * @Author Liujx
 * Version 1.0
 **/
public interface BrandService {

    /**
     * 根据类型id查询品牌列表
     * @param cId
     * @return
     */
    List<BrandDto> getListByCId(Long cId);
}
