package cn.est.service;

import cn.est.dto.ClassifyDto;

import java.util.List;

/**
 * @Description 商品分类业务层接口
 * @Date 2019-08-15 10:25
 * @Author Liujx
 * Version 1.0
 **/
public interface ClassifyService {

    /**
     * 首页商品分类树
     * @return
     */
    List<ClassifyDto> getTrees();
}
