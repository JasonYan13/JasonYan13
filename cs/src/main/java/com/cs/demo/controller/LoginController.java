package com.cs.demo.controller;

import com.cs.demo.dto.BaseResponse;
import com.cs.demo.dto.request.LoginReqDto;
import com.cs.demo.dto.response.LoginRespDto;
import com.cs.demo.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.cs.demo.constants.WebConstants.REQUEST_HEADER_ACCESS_TOKEN;

/**
 * @description: 登录接口
 **/
@RestController
@RequestMapping("/api/v1/login")
@Api(tags = "登录接口")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("")
    public BaseResponse<LoginRespDto> login(@RequestBody LoginReqDto reqDto) {
        return new BaseResponse(loginService.login(reqDto));
    }

    @PostMapping("/exit")
    public BaseResponse<Boolean> exit(@RequestHeader(value = REQUEST_HEADER_ACCESS_TOKEN) String token) {
        loginService.exit(token);
        return new BaseResponse(Boolean.TRUE);
    }
}
