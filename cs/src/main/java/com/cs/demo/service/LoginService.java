package com.cs.demo.service;

import com.cs.demo.dto.request.LoginReqDto;
import com.cs.demo.dto.response.LoginRespDto;

/**
 * @author
 * @description:
 **/
public interface LoginService {

  /**
   * 用户登录
   * @param reqDto
   * @return
   */
  LoginRespDto login(LoginReqDto reqDto);

  /**
   * 退出登录
   */
  void exit(String token);
}
