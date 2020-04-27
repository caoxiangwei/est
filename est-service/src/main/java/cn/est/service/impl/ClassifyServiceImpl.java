package cn.est.service.impl;

import cn.est.constants.Constants;
import cn.est.utils.oss.OssConfig;
import cn.est.utils.reids.RedisUtils;
import cn.est.mapper.ClassifyMapper;
import cn.est.dto.BrandDto;
import cn.est.dto.ClassifyDto;
import cn.est.dto.ModelDto;
import cn.est.req.ModelReq;
import cn.est.service.BrandService;
import cn.est.service.ClassifyService;
import cn.est.service.ModelService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 商品分类业务层实现
 * @Date 2019-08-15 10:26
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class ClassifyServiceImpl implements ClassifyService {

    Logger log = LoggerFactory.getLogger(ClassifyServiceImpl.class);

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 首页商品分类树
     * @return
     */
    @Override
    public List<ClassifyDto> getTrees() {
        List<ClassifyDto> classifyDtoList = null;
        // 从redis中查询，如果查询不到再查库
        String value = redisUtils.getValue(Constants.Redis.KEY_HOME_TREE);
        if (StringUtils.isBlank(value)) {
            log.info("首页商品分类树--查库");
            // 1.查询所有分类
            classifyDtoList = classifyMapper.selectList();
            for (ClassifyDto Classify : classifyDtoList) {
                if (Classify != null && Classify.getId() != null) {
                    Long cId = Classify.getId();
                    // 2.查询当前分类的品牌
                    List<BrandDto> brandDtoList = brandService.getListByCId(cId);
                    for (BrandDto brandDto : brandDtoList) {
                        if (brandDto != null && brandDto.getId() != null) {
                            Long bId = brandDto.getId();
                            // 3.查询当前品牌对应的商品
                            ModelReq modelReq = new ModelReq(cId, bId, null, 0, Constants.Home.MODEL_SIZE);
                            List<ModelDto> modelList = modelService.getModelList(modelReq);
                            brandDto.setModelList(modelList);
                        }
                    }
                    Classify.setBrandList(brandDtoList);
                    // 格式化商品类型数据
//                    formatClassify(classifyVo);
                }
            }
            // 4.把分类树信息放在redis中
            redisUtils.putValue(Constants.Redis.KEY_HOME_TREE, JSONObject.toJSONString(classifyDtoList));
        } else {
            log.info("首页商品分类树--查reids：{}", value);
            classifyDtoList = JSONObject.parseArray(value, ClassifyDto.class);
        }
        return classifyDtoList;
    }

}
