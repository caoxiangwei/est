package cn.est.service.impl;

import cn.est.constants.Constants;
import cn.est.utils.MathUtils;
import cn.est.utils.StringUtil;
import cn.est.mapper.EvaluateMapper;
import cn.est.mapper.MalfunctionMapper;
import cn.est.mapper.MalfunctionOptionsMapper;
import cn.est.dto.EvaluateDetailDto;
import cn.est.dto.EvaluateDto;
import cn.est.dto.ModelDto;
import cn.est.pojo.Evaluate;
import cn.est.pojo.Malfunction;
import cn.est.pojo.MalfunctionOptions;
import cn.est.service.EvaluateService;
import cn.est.service.EvaluateDetailService;
import cn.est.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description 旧品评估业务层实现
 * @Date 2019-08-16 17:06
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

    Logger log = LoggerFactory.getLogger(EvaluateServiceImpl.class);

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Autowired
    EvaluateDetailService evaluateDetailService;

    @Autowired
    private MalfunctionMapper malfunctionMapper;

    @Autowired
    private MalfunctionOptionsMapper optionsMapper;

    @Autowired
    private ModelService modelService;

    /**
     * 旧品维修估价
     * @param mId
     * @param optionIds
     * @return
     */
    @Override
    public EvaluateDto assess(Long mId, String optionIds, Long uId) {
        EvaluateDto evaluateDto = null;
        // 评估
        BigDecimal totalPrice = new BigDecimal(0);   // 总维修价格
        List<MalfunctionOptions> optionsList = null;
        // 1.查出用户选择的选项及问题数据
        Set<Long> optionIdSet = StringUtil.string2LongSet(optionIds , Constants.Connnector.COMMA_);
        // 保存评估详情信息
        for (Long oId :optionIdSet){
            if(oId != null){
                MalfunctionOptions option = optionsMapper.getMalfunctionOptionsById(oId);
                if (option != null && option.getMalfId() != null){
                    Malfunction malfunction = malfunctionMapper.getMalfunctionById(option.getMalfId());
                    Integer processType = option.getProcessType();
                    // 单项问题维修价格，如果是更换零件，那么维修价格比例为10
                    BigDecimal unitPrice = malfunction.getMaintainTopPrice().multiply(new BigDecimal(option.getRatio() * 0.1));
                    totalPrice = totalPrice.add(unitPrice);
                    if(optionsList == null){
                        optionsList = new ArrayList<>();
                    }
                    optionsList.add(option);
                }
            }
        }
        totalPrice = MathUtils.formatDecimal(totalPrice, Constants.DECIMAL_DIGITS);
        Evaluate evaluate = new Evaluate(mId, uId, totalPrice);
        // 保存评估信息
        save(evaluate);
        // 批量保存维修评估详情
        evaluateDetailService.saveBatchByOptions(evaluate, optionsList);
        evaluateDto = new EvaluateDto(evaluate);
        return evaluateDto;
    }


    /**
     * 查询旧品评估详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public EvaluateDto getById(Long id) {
        EvaluateDto evaluateDto = null;
        Evaluate evaluate = evaluateMapper.getEvaluateById(id);
        if(evaluate != null){
            evaluateDto = new EvaluateDto();
            ModelDto model = modelService.getById(evaluate.getModelId());
            evaluateDto.setModel(model);
            evaluateDto.setId(evaluate.getId());
            evaluateDto.setModelId(evaluate.getModelId());
            evaluateDto.setPrice(evaluate.getPrice());
            evaluateDto.setSubscription(evaluate.getSubscription());
            // 封装评估详细信息
            List<EvaluateDetailDto> detailList = evaluateDetailService.getByEId(evaluate.getId());
            evaluateDto.setDetailList(detailList);
        }
        return evaluateDto;
    }


    /**
     * 保存评估信息
     * @param evaluate
     * @return
     * @throws Exception
     */
    public int save(Evaluate evaluate) {
        int succ = evaluateMapper.insertEvaluate(evaluate);
        return  succ;
    }
}
