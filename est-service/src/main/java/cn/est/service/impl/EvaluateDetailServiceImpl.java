package cn.est.service.impl;

import cn.est.mapper.EvaluateDetailMapper;
import cn.est.dto.EvaluateDetailDto;
import cn.est.pojo.Evaluate;
import cn.est.pojo.EvaluateDetail;
import cn.est.pojo.MalfunctionOptions;
import cn.est.service.EvaluateDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 旧品评估详情业务层实现
 * @Date 2019-08-16 17:06
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class EvaluateDetailServiceImpl implements EvaluateDetailService {

    Logger log = LoggerFactory.getLogger(EvaluateDetailServiceImpl.class);

    @Autowired
    private EvaluateDetailMapper evaluateDetailMapper;


    /**
     * 保存维修评估详情
     * @param evaluateDetail
     * @return
     */
    @Override
    public int save(EvaluateDetail evaluateDetail) {
        return evaluateDetailMapper.insertEvaluateDetail(evaluateDetail);
    }

    /**
     * 批量保存维修评估详情
     * @param evaluate
     * @param optionsList
     */
    @Override
    public int saveBatchByOptions(Evaluate evaluate, List<MalfunctionOptions> optionsList) {
        int succ = 0;
        for(MalfunctionOptions option :optionsList){
            if(option != null){
                EvaluateDetail evaluateDetail = new EvaluateDetail(evaluate.getId(), option.getMalfId(), option.getId(), option.getOptionName());
                evaluateDetail.setCreatedUserId(evaluate.getCreatedUserId());
                succ = save(evaluateDetail);
            }
        }
        return succ;
    }

    /**
     * 根据评估信息id，查询评估详情数据
     * @param eId
     * @return
     */
    @Override
    public List<EvaluateDetailDto> getByEId(Long eId) {
        List<EvaluateDetailDto> list = evaluateDetailMapper.selectListByEId(eId);;
        return list;
    }
}
