package com.cs.demo.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @description: 用户登录
 **/
@Data
public class LoginReqDto implements Serializable {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPwd;
}
