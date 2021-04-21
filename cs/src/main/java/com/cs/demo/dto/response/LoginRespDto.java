package com.cs.demo.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @description:
 **/
@Data
public class LoginRespDto implements Serializable {
  /**
   * 登录标识
   */
  private String token;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 用户id
   */
  private String userName;
  /**
   * 角色编码
   */
  private String roleCode;
}
