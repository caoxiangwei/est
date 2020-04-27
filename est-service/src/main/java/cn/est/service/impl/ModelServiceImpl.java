package cn.est.service.impl;
import cn.est.constants.Constants;
import cn.est.dto.Page;
import cn.est.utils.oss.OssConfig;
import cn.est.mapper.ModelMapper;
import cn.est.dto.ModelDto;
import cn.est.pojo.Model;
import cn.est.req.ModelReq;
import cn.est.service.ModelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 商品模型业务层实现
 * @Date 2019-08-15 15:05
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    Logger log = LoggerFactory.getLogger(ModelServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OssConfig ossConfig;


    /**
     * 分页查询商品模型数据
     * @param modelReq
     * @return
     */
    @Override
    public Page<ModelDto> getModelPage(ModelReq modelReq) {
        Integer total=modelMapper.getModelCount(modelReq);
        Page<ModelDto> page = new Page<ModelDto>(modelReq.getPageNo(),modelReq.getPageSize(),total);
        modelReq.setBeginPos(page.getBeginPos());
        modelReq.setPageNo(page.getPageNo());
        modelReq.setPageSize(page.getPageSize());
        List<ModelDto> modelList = modelMapper.selectList(modelReq);
        formatModelList(modelList);
        page.setList(modelList);
        return page;
    }



    /**
     * 根据id查询商品模型详情
     * @param id
     * @return
     */
    @Override
    public ModelDto getById(Long id) {
        ModelDto dto = null;
        Model model = modelMapper.getModelById(id);
        if(model != null && model.getIsDelete() != null && !model.getIsDelete().equals(Constants.EST_YES)){
            dto = createDtoByModel(model);
            formatModel(dto);
        }
        return dto;
    }

    /**
     * 根据vo对象查询商品类型列表
     * @param modelReq
     * @return
     */
    @Override
    public List<ModelDto> getModelList(ModelReq modelReq) {
        List<ModelDto> modelList = modelMapper.selectList(modelReq);
        // 格式化商品模型数据
        formatModelList(modelList);
        return  modelList;
    }

    /**
     * 格式化model数据
     * @param modelList
     */
    private void formatModelList(List<ModelDto> modelList) {
        if(modelList != null && !modelList.isEmpty()){
            for (ModelDto model :modelList){
                formatModel(model);
            }
        }
    }

    /**
     * 格式化model数据
     * @param model
     */
    private void formatModel(ModelDto model) {
        if(model != null){
            if(!StringUtils.isBlank(model.getFaceImg())){
                model.setFaceImg(ossConfig.getOssWebUrl() + model.getFaceImg());
            }
            if(!StringUtils.isBlank(model.getContentImg())){
                model.setContentImg(ossConfig.getOssWebUrl() + model.getContentImg());
            }
        }
    }

    /**
     * 格式化model数据
     * @param model
     */
    private ModelDto createDtoByModel(Model model) {
        ModelDto dto = null;
        if(model != null){
            dto = new ModelDto(model);
        }
        return dto;
    }
}
