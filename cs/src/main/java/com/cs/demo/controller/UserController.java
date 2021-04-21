package com.cs.demo.controller;

import com.cs.demo.dto.BaseResponse;
import com.cs.demo.dto.PageResult;
import com.cs.demo.dto.request.UserReqDto;
import com.cs.demo.dto.response.UserRespDto;
import com.cs.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 登录接口
 **/
@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "登录接口")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody UserReqDto reqDto) {
        userService.addUser(reqDto, getUserInfo());
        return new BaseResponse(Boolean.TRUE);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody UserReqDto reqDto) {
        userService.updateUser(reqDto, getUserInfo());
        return new BaseResponse(Boolean.TRUE);
    }

    @PostMapping("/upPwd")
    public BaseResponse<Boolean> updatePwd(@RequestBody UserReqDto reqDto) {
        userService.updateUserPwd(reqDto, getUserInfo());
        return new BaseResponse(Boolean.TRUE);
    }

    @PostMapping("/del")
    public BaseResponse<Boolean> del(@RequestBody UserReqDto reqDto) {
        userService.delUser(reqDto);
        return new BaseResponse(Boolean.TRUE);
    }

    @PostMapping("/page")
    public BaseResponse<PageResult<UserRespDto>> page(@RequestBody UserReqDto reqDto) {
        return new BaseResponse(userService.page(reqDto,getUserInfo()));
    }
}
