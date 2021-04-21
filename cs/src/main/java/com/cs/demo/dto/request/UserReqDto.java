package com.cs.demo.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @description: 用户登录
 **/
@Data
public class UserReqDto implements Serializable {
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 旧密码
     */
    private String oldPwd;
    /**
     * 新密码
     */
    private String newPwd;

    private Integer pageNum;

    private Integer pageSize;
}
