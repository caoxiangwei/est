package cn.est.mapper;

import cn.est.dto.UsersDto;
import cn.est.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户功能数据层接口
 */
public interface UsersMapper {

	public Users getUsersById(@Param(value = "id") Long id);

	public List<Users>	getUsersListByMap(Map<String, Object> param);

	public Integer insertUsers(Users users);

	/**
	 * 根据手机号查询用户信息
	 * @param phone
	 * @return
	 */
	UsersDto selectByPhone(String phone);

	/**
	 * 根据openId查询用户信息
	 * @param openId
	 * @return
	 */
	UsersDto selectByOpenId(String openId);

	/**
	 * 根据账号、密码查询用户信息
	 * @param accont
	 * @param password
	 * @return
	 */
	UsersDto selectByAccountAndPass(String accont, String password);
}
