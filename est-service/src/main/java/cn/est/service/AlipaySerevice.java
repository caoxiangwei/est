package cn.est.service;

import cn.est.dto.MaintainOrderDto;
import com.alipay.api.AlipayApiException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 支付宝支付业务层接口
 * @Date 2019-08-20 15:43
 * @Author Liujx
 * Version 1.0
 **/
public interface AlipaySerevice {

    /**
     * 阿里支付
     * @param alipayBean
     * @param maintainOrderDto
     * @return
     */
    String pay(AlipayBean alipayBean, MaintainOrderDto maintainOrderDto);

    /**
     * 阿里支付同步回调处理
     * @param request
     * @return
     */
    int syncNotify(HttpServletRequest request) throws AlipayApiException;

    /**
     * 阿里支付异步回调处理
     * @param request
     */
    int asyncNotify(HttpServletRequest request) throws AlipayApiException;
}
