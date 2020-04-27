package cn.est.mapper;

import cn.est.dto.MaintainOrderDto;
import cn.est.pojo.MaintainOrder;

public interface MaintainOrderMapper {

	public Integer insertMaintainOrder(MaintainOrder maintainOrder);

	public Integer updateMaintainOrder(MaintainOrder maintainOrder);

	/**
	 * 根据订单编号查询订单Dto信息
	 * @param orderNo
	 * @return
	 */
	MaintainOrderDto selectDtoByOrderNo(String orderNo);

	/**
	 * 根据订单编号查询订单信息
	 * @param orderNo
	 * @return
	 */
	MaintainOrder selectByOrderNo(String orderNo);
}
