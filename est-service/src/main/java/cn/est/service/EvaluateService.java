package cn.est.service;

import cn.est.dto.EvaluateDto;

/**
 * @Description 旧品评估业务层接口
 * @Date 2019-08-16 17:05
 * @Author Liujx
 * Version 1.0
 **/
public interface EvaluateService {

    /**
     * 旧品维修估价
     * @param mId
     * @param optionIds
     * @return
     */
    EvaluateDto assess(Long mId, String optionIds, Long uId);

    /**
     * 根据id查询评估详情
     * @param id
     * @return
     */
    EvaluateDto getById(Long id);
}
