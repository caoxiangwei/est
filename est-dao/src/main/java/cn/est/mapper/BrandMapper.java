package cn.est.mapper;

import cn.est.dto.BrandDto;

import java.util.List;

public interface BrandMapper {

	/**
	 * 根据类型id查询品牌列表
	 * @param classifyId
	 * @return
	 */
    List<BrandDto> selectListByCId(Long classifyId);
}
