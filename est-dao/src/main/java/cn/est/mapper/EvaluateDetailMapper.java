package cn.est.mapper;
import cn.est.dto.EvaluateDetailDto;
import cn.est.pojo.EvaluateDetail;

import java.util.List;

public interface EvaluateDetailMapper {

	public Integer insertEvaluateDetail(EvaluateDetail evaluateDetail);

	/**
	 * 根据评估信息id，查询评估详情数据
	 * @param evaluateId
	 * @return
	 */
	List<EvaluateDetailDto> selectListByEId(Long evaluateId);
}
