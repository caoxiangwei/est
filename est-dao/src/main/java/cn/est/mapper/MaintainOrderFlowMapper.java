package cn.est.mapper;

import cn.est.dto.MaintainOrderFlowDto;
import cn.est.pojo.MaintainOrderFlow;

import java.util.List;

public interface MaintainOrderFlowMapper {

	public Integer insertMaintainOrderFlow(MaintainOrderFlow maintainOrderFlow);

	/**
	 * 根据交易订单号查询流水信息
	 * @param orderNo
	 * @return
	 */
	List<MaintainOrderFlowDto> selectByOrderNo(String orderNo);
}
