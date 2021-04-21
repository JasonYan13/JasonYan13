package com.cs.demo.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @description: 用户登录
 **/
@Data
public class UserRespDto implements Serializable {
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
    private String email;
    /**
     * 邮箱
     */
    private String roleName;

    private Date updateTime;
}
