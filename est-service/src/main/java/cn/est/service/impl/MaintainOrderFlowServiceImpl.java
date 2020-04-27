package cn.est.service.impl;

import cn.est.mapper.MaintainOrderFlowMapper;
import cn.est.mapper.UsersMapper;
import cn.est.dto.MaintainOrderFlowDto;
import cn.est.constants.MaintainStatusEnum;
import cn.est.pojo.MaintainOrder;
import cn.est.pojo.MaintainOrderFlow;
import cn.est.pojo.Users;
import cn.est.service.MaintainOrderFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 维修订单流水信息
 * @Date 2019-08-20 18:46
 * @Author Liujx
 * Version 1.0
 **/
@Service
@Transactional
public class MaintainOrderFlowServiceImpl implements MaintainOrderFlowService {

    Logger log = LoggerFactory.getLogger(MaintainOrderFlowServiceImpl.class);


    @Autowired
    private MaintainOrderFlowMapper maintainOrderFlowMapper;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 根据订单编号查询订单流水信息
     * @param orderNo
     * @return
     */
    @Override
    public List<MaintainOrderFlowDto> getByOrderNo(String orderNo) {
        List<MaintainOrderFlowDto> flowList = maintainOrderFlowMapper.selectByOrderNo(orderNo);
        return flowList;
    }


    /**
     * 根据订单状态保存流水状态
     * @param maintainOrder
     * @return
     */
    @Override
    public int saveByMaintainOrder(MaintainOrder maintainOrder) {
        int succ = 0;
        Users user = usersMapper.getUsersById(maintainOrder.getUserId());
        MaintainStatusEnum maintainStatusEnum = MaintainStatusEnum.getByCode(maintainOrder.getStatus());
        MaintainOrderFlow maintainOrderFlow = new MaintainOrderFlow(maintainOrder.getOrderNo(), maintainStatusEnum
                , maintainOrder.getUserId(), user.getUserName());
        maintainOrderFlow.setCreatedUserId(maintainOrder.getUserId());
        succ = maintainOrderFlowMapper.insertMaintainOrderFlow(maintainOrderFlow);
        return succ;
    }
}
