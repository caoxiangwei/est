package cn.est.mapper;

import cn.est.dto.ModelDto;
import cn.est.pojo.Model;
import cn.est.req.ModelReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelMapper {

	public Model getModelById(@Param(value = "id") Long id);

	/**
	 * 根据vo对象查询商品类型列表
	 * @param modelReq
	 * @return
	 */
	List<ModelDto> selectList(ModelReq modelReq);

	public Integer getModelCount(ModelReq modelReq);

}
