package com.cs.demo.service.impl;

import com.cs.demo.eo.UserEo;
import com.cs.demo.eo.UserEoExample;
import com.cs.demo.mapper.LoginEoMapper;
import com.cs.demo.mapper.UserEoMapper;
import com.cs.demo.dto.request.LoginReqDto;
import com.cs.demo.dto.response.LoginRespDto;
import com.cs.demo.eo.LoginEo;
import com.cs.demo.eo.LoginEoExample;
import com.cs.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @description:
 **/
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
  @Autowired
  private UserEoMapper userEoMapper;
  @Autowired
  private LoginEoMapper loginEoMapper;

  /**
   * 用户登录
   * @param reqDto
   * @return
   */
  @Override
  public LoginRespDto login(LoginReqDto reqDto) {
    if (StringUtils.isAnyBlank(reqDto.getUserName(), reqDto.getUserPwd())) {
      throw new RuntimeException("用户名或密码为空");
    }
    UserEoExample eo = new UserEoExample();
    eo.createCriteria().andUserCodeEqualTo(reqDto.getUserName());
    eo.createCriteria().andPwdEqualTo(reqDto.getUserPwd());
    List<UserEo> userEoList = userEoMapper.selectByExample(eo);
    if (CollectionUtils.isEmpty(userEoList)) {
      throw new RuntimeException("用户名或密码错误");
    }
    UserEo user = userEoList.get(0);
    LoginRespDto respDto = new LoginRespDto();
    String token = UUID.randomUUID().toString().replaceAll("-", "");
    respDto.setToken(token);
    respDto.setUserId(user.getId());
    respDto.setUserName(user.getUserName());
    respDto.setRoleCode(user.getRoleCode());
    LoginEo loginEo = new LoginEo();
    loginEo.setUserCode(user.getUserCode());
    loginEo.setToken(token);
    loginEo.setUserId(user.getId());
    loginEo.setLoginFlag("1");
    loginEoMapper.insert(loginEo);
    return respDto;

  }

  /**
   * 退出登录
   * @param token
   */
  @Override
  public void exit(String token) {
    LoginEoExample eoExample = new LoginEoExample();
    eoExample.createCriteria().andTokenEqualTo(token);
    loginEoMapper.deleteByExample(eoExample);
  }
}
