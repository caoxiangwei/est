package cn.est.service.impl;

import cn.est.constants.Constants;
import cn.est.utils.MD5Utils;
import cn.est.utils.UUIDUtils;
import cn.est.utils.UrlUtils;
import cn.est.utils.reids.RedisKeyUtils;
import cn.est.utils.reids.RedisUtils;
import cn.est.mapper.UsersMapper;
import cn.est.dto.UsersDto;
import cn.est.pojo.Users;
import cn.est.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户功能业务层实现
 */
@Service
@Transactional
class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Users getById(Long id) {
        Users user = usersMapper.getUsersById(id);
        return user;
    }

    @Override
    public List<Users> list() {
        return usersMapper.getUsersListByMap(null);
    }


    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    @Override
    public UsersDto getByPhone(String phone) {
        UsersDto users = usersMapper.selectByPhone(phone);
        return users;
    }

    /**
     * 用户登录--验证码
     * @param phone
     * @return
     */
    @Override
    public UsersDto loginByPhone(String phone) {
        UsersDto userDto = usersMapper.selectByPhone(phone);
        // 1.如果用户不存在，说明是第一次登录，创建一个用户
        if(userDto == null){
            Users users = new Users(phone, null, phone);
            usersMapper.insertUsers(users);
            setToken(users);
        }else {
            // 2.登录，将用户信息缓存到redis中
            setToken(userDto);
        }
        // 3.将验证码置为无效
        String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_SMS, Constants.Sms.TYPE_REGIST_OR_LOGIN.toString(), phone);
        redisUtils.delete(key);
        return userDto;
    }

    /**
     * 用户登录
     * @param phone
     * @return
     */
    @Override
    public UsersDto login(String phone, String password) {
        UsersDto usersDto = usersMapper.selectByPhone(phone);
        String mdPass = MD5Utils.MD5(password + Constants.Connnector.UNDERLINE + usersDto.getSalt());
        // 校验密码
        usersDto = usersMapper.selectByAccountAndPass(phone, password);
        if(!usersDto.getPassword().equals(mdPass)){
            log.info("密码错误");
            return null;
        }
        // 2.登录，将用户信息缓存到redis中
        setToken(usersDto);
        return usersDto;
    }

    /**
     * 设置秘密
     * @param password
     * @param affirmPassword
     */
    /*@Override
    public int password(String password, String affirmPassword, String token) {
        int succ = 0;
        Date currentDate = DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
        String salt = UUIDUtils.getUUID32();
        Users users = getLoginUser(token);
        users.setSalt(salt);
        users.setPassword(MD5Utils.MD5(password + Constants.Connnector.UNDERLINE + salt));
        users.setUpdatedUserId(users.getId());
        users.setUpdatedTime(currentDate);
        succ = usersMapper.updateUsers(users);
        return succ;
    }*/

    /**
     * 登出
     * @param token
     * @return
     */
    @Override
    public int logout(String token) {
        int succ = 0;
        String key = RedisKeyUtils.formatKeyWithPrefix(token);
        redisUtils.delete(key);
        if(redisUtils.getValue(key) == null){
            succ = 1;
        }
        return succ;
    }

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    @Override
    public UsersDto getByOpenId(String openId) {
        UsersDto usersDto = usersMapper.selectByOpenId(openId);
        return usersDto;
    }

    /**
     * 微信扫描登录、注册
     * @param accessToken
     * @param openId
     * @return
     * @throws Exception
     */
    @Override
    public UsersDto loginByWechat(String accessToken, String openId) {
        UsersDto usersDto = usersMapper.selectByOpenId(openId);
        // 1.如果用户不存在，说明是第一次登录，创建一个用户
        if(usersDto == null){
            Users users = new Users(null, openId, openId);
            Map<String,Object> map = wechatUserInfo(accessToken, openId);
            if (map != null){
                if(map.get("nickname") != null){
                    users.setUserName(map.get("nickname").toString());
                }
                if(map.get("headimgurl") != null){
                    users.setFaceUrl(map.get("headimgurl").toString());
                }
                if(map.get("sex") != null){
                    users.setSex(Integer.parseInt(map.get("sex").toString()));
                }
            }
            usersMapper.insertUsers(users);
            usersDto = new UsersDto(users);
        }
        // 2.登录，将用户信息缓存到redis中
        setToken(usersDto);
        return usersDto;
    }

    /**
     * 设置用户登录
     * @param users
     */
    public void setToken(Users users) {
        String token = UUIDUtils.getUUID32();
        UsersDto usersDto = new UsersDto();
        setToken(usersDto);
    }

    /**
     * 设置用户登录
     * @param usersDto
     */
    public void setToken(UsersDto usersDto) {
        String token = UUIDUtils.getUUID32();
        usersDto.setToken(token);
        String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_TOKEN, token);
        // 暂时设置永不过期
        redisUtils.putValue(key, JSON.toJSON(usersDto).toString(), Constants.Duration.HALF_AN_HOUR);
//        redisUtils.putValue(key, JSON.toJSON(usersDto).toString());
    }

    /**
     * 获取登录用户
     * @param token
     */
    public UsersDto getLoginUser(String token) {
        UsersDto usersDto = null;
        String key = RedisKeyUtils.formatKeyWithPrefix(Constants.Redis.PREFIX_TOKEN, token);
        String value = redisUtils.getValue(key);
        if(!StringUtils.isBlank(value)){
            usersDto = JSONObject.parseObject(value, UsersDto.class);
            log.info("获取到用户登录信息users:{}", value);
        }
        return usersDto;
    }


    /**
     * 判断用户登录状态
     * @param token
     * @return
     */
    public boolean checkLogin(String token) {
        boolean flag = true;
        if(getLoginUser(token) == null){
            flag = false;
            log.info("用户未登录：token{}", token);
        }
        return flag;
    }


    /**
     * 获取微信用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    public Map<String, Object> wechatUserInfo(String accessToken, String openid){
        Map<String, Object> userInfo = null;
        try {
            //加载用户信息
            String userInfoJson = UrlUtils.loadURL("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid);
            userInfo = JSON.parseObject(userInfoJson, Map.class);
        } catch (Exception e) {
            log.error("授权失败", e.getMessage());
        }
        return  userInfo;
    }

}
