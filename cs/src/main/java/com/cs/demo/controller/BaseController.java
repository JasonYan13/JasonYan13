package com.cs.demo.controller;

import com.cs.demo.config.ThreadLocalFactory;
import com.cs.demo.eo.UserEo;
import com.cs.demo.eo.UserEoKey;
import com.cs.demo.mapper.UserEoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Description :  Controller 抽象功能
 */
@Configuration
@Slf4j
public class BaseController {
    @Autowired
    private UserEoMapper userEoMapper;

    public Long getUserId() {
        return ThreadLocalFactory.getThreadLocalData(Long.class);
    }

    /**
     * 获取当前登陆用户信息
     *
     * @return
     */
    public UserEo getUserInfo() {
        UserEoKey eoKey = new UserEoKey();
        eoKey.setId(getUserId());
        return userEoMapper.selectByPrimaryKey(eoKey);
    }


}
