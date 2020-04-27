package cn.est.service.impl;

import cn.est.mapper.EvaluateDetailMapper;
import cn.est.mapper.EvaluateMapper;
import cn.est.mapper.MalfunctionMapper;
import cn.est.mapper.MalfunctionOptionsMapper;
import cn.est.dto.MalfunctionDto;
import cn.est.dto.MalfunctionOptionsDto;
import cn.est.service.MalfunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 问题状况业务层实现
 * @Date 2019-08-16 16:12
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class MalfunctionServiceImpl implements MalfunctionService {

    Logger log = LoggerFactory.getLogger(MalfunctionServiceImpl.class);

    @Autowired
    private MalfunctionMapper malfunctionMapper;

    @Autowired
    private MalfunctionOptionsMapper optionsMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Autowired
    private EvaluateDetailMapper evaluateDetailMapper;

    /**
     * 旧品维修评估维度
     * @param mId
     * @return
     */
    @Override
    public List<MalfunctionDto> getMalfunctionList(Long mId) {
        List<MalfunctionDto> malfunctionDtoList =  malfunctionMapper.selectListByModelId(mId);
        if(malfunctionDtoList != null && !malfunctionDtoList.isEmpty()){
            for (MalfunctionDto dto :malfunctionDtoList){
                if(dto != null && dto.getId() != null){
                    List<MalfunctionOptionsDto> optionsList = optionsMapper.selectListByMalfId(dto.getId());
                    if(optionsList != null && !optionsList.isEmpty()){
                        dto.setOptionsList(optionsList);
                    }
                }
            }
        }
        return malfunctionDtoList;
    }


}
