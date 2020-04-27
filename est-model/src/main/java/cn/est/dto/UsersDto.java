package cn.est.dto;

import cn.est.pojo.Users;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息包装类
 */
@ApiModel(value = "UsersDto", description = "用户信息包装类")
public class UsersDto {

    public UsersDto(){}

    public UsersDto(Users users){
        this.id = users.getId();
        this.account = users.getAccount();
        this.sex = users.getSex();
        this.faceUrl = users.getFaceUrl();
        this.userName = users.getUserName();
    }

    @ApiModelProperty("用户id")
    private Long id;
    //账号（手机号）
    @ApiModelProperty("账号")
    private String account;
    //密码
    @ApiModelProperty(hidden = true)
    private String password;
    //昵称
    @ApiModelProperty("昵称")
    private String userName;
    //性别（0:女,1:男,2:保密）
    @ApiModelProperty("性别（0:女,1:男,2:保密）")
    private Integer sex;
    //头像
    @ApiModelProperty("头像")
    private String faceUrl;
    //盐
    @ApiModelProperty(hidden = true)
    private String salt;

    // 用户登录token
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
