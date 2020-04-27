package cn.est.mapper;

import cn.est.dto.ClassifyDto;

import java.util.List;

public interface ClassifyMapper {

	/**
	 * 查询所有分类
	 * @return
	 */
    List<ClassifyDto> selectList();
}
