package cn.est.mapper;
import cn.est.pojo.PayOrder;

public interface PayOrderMapper {

	public Integer insertPayOrder(PayOrder payOrder);

	public Integer updatePayOrder(PayOrder payOrder);

	/**
	 * 根据支付订单编号查询支付订单信息
	 * @param payNo
	 * @return
	 */
	PayOrder selectByPayNo(String payNo);
}
