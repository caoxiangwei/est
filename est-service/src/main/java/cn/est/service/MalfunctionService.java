package cn.est.service;

import cn.est.dto.MalfunctionDto;

import java.util.List;

/**
 * @Description 问题状况业务层接口
 * @Date 2019-08-16 16:11
 * @Author Liujx
 * Version 1.0
 **/
public interface MalfunctionService {


    /**
     * 查询旧品维修评估维度
     * @param mId
     * @return
     */
    List<MalfunctionDto> getMalfunctionList(Long mId);

}
