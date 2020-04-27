package cn.est.service.impl;

import cn.est.utils.oss.OssConfig;
import cn.est.mapper.BrandMapper;
import cn.est.dto.BrandDto;
import cn.est.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 品牌信息业务层实现
 * @Date 2019-08-20 19:51
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);


    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private OssConfig ossConfig;

    /**
     * 根据类型id查询品牌列表
     * @param cId
     * @return
     */
    @Override
    public List<BrandDto> getListByCId(Long cId) {
        List<BrandDto> brandDtoList = brandMapper.selectListByCId(cId);
        // 格式化品牌数据
        formatBrand(brandDtoList);
        return brandDtoList;
    }

    /**
     * 格式化品牌列表数据数据
     * @param brandVoList
     */
    private void formatBrand(List<BrandDto> brandVoList) {
        for (BrandDto brandDto :brandVoList){
            if(brandDto != null && brandDto.getId() != null){
                Long bId = brandDto.getId();
                // 格式化品牌数据
                formatBrand(brandDto);
            }
        }
    }


    /**
     * 格式化品牌数据
     * @param brandDto
     */
    private void formatBrand(BrandDto brandDto) {
        if(!StringUtils.isBlank(brandDto.getLogo())){
            brandDto.setLogo(ossConfig.getOssWebUrl() + brandDto.getLogo());
        }
    }
}
