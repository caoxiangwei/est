package cn.est.service;

import cn.est.dto.UsersDto;
import cn.est.pojo.Users;

import java.util.List;

/**
 * 用户号功能业务层接口
 */
public interface UserService {

    Users getById(Long id);

    List<Users> list();

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    UsersDto getByPhone(String phone);

    /**
     * 创建用户信息
     * @param phone
     * @return
     */
//    UsersDto regist(String phone);

    /**
     * 用户登录--手机号
     * @param phone
     * @return
     */
    UsersDto loginByPhone(String phone);

    /**
     * 用户登录
     * @param phone
     * @return
     */
    UsersDto login(String phone, String password);

    /**
     * 获取登录用户
     * @param token
     * @return
     */
    UsersDto getLoginUser(String token);

    /**
     * 验证用户登录
     * @param token
     * @return
     */
    boolean checkLogin(String token);

    /**
     * 设置秘密
     * @param password
     * @param affirmPassword
     */
//    int password(String password, String affirmPassword, String token);

    /**
     * 登出
     * @param token
     * @return
     */
    int logout(String token);

    /**
     * 根据openId查询用户想你想
     * @param openId
     * @return
     */
    UsersDto getByOpenId(String openId);

    /**
     * 微信扫描登录、注册
     * @param accessToken
     * @param openId
     * @return
     * @throws Exception
     */
    UsersDto loginByWechat(String accessToken, String openId);
}
