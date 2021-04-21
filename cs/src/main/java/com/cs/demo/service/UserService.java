package com.cs.demo.service;

import com.cs.demo.dto.PageResult;
import com.cs.demo.dto.request.UserReqDto;
import com.cs.demo.dto.response.UserRespDto;
import com.cs.demo.eo.UserEo;

/**
 *
 * @Author:
 */
public interface UserService {
  void addUser(UserReqDto reqDto, UserEo userEo);

  void updateUser(UserReqDto reqDto, UserEo currUser);

  void updateUserPwd(UserReqDto reqDto,UserEo userEo);

  void delUser(UserReqDto reqDto);

  PageResult<UserRespDto> page(UserReqDto reqDto, UserEo currUser);
}
