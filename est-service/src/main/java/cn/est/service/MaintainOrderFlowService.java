package cn.est.service;

import cn.est.dto.MaintainOrderFlowDto;
import cn.est.pojo.MaintainOrder;

import java.util.List;

/**
 * @Description 维修订单流水信息
 * @Date 2019-08-20 18:45
 * @Author Liujx
 * Version 1.0
 **/
public interface MaintainOrderFlowService {

    /**
     * 根据订单编号查询订单流水信息
     * @param orderNo
     * @return
     */
    List<MaintainOrderFlowDto> getByOrderNo(String orderNo);

    /**
     * 根据订单状态保存流水状态
     * @param maintainOrder
     */
    int saveByMaintainOrder(MaintainOrder maintainOrder);
}
