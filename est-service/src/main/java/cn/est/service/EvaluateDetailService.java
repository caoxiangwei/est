package cn.est.service;

import cn.est.dto.EvaluateDetailDto;
import cn.est.pojo.Evaluate;
import cn.est.pojo.EvaluateDetail;
import cn.est.pojo.MalfunctionOptions;

import java.util.List;

/**
 * @Description 旧品评估业务详情层接口
 * @Date 2019-08-16 17:05
 * @Author Liujx
 * Version 1.0
 **/
public interface EvaluateDetailService {

    /**
     * 保存维修评估详情
     * @param evaluateDetail
     * @return
     */
    int save(EvaluateDetail evaluateDetail);

    /**
     * 批量保存维修评估详情
     * @param evaluate
     * @param optionsList
     */
    int saveBatchByOptions(Evaluate evaluate, List<MalfunctionOptions> optionsList);

    /**
     * 根据评估信息id，查询评估详情数据
     * @param id
     * @return
     */
    List<EvaluateDetailDto> getByEId(Long id);
}
