package com.cs.demo.service.impl;

import com.cs.demo.constants.WebConstants;
import com.cs.demo.dto.PageResult;
import com.cs.demo.dto.request.UserReqDto;
import com.cs.demo.dto.response.UserRespDto;
import com.cs.demo.eo.*;
import com.cs.demo.mapper.RoleEoMapper;
import com.cs.demo.mapper.UserEoMapper;
import com.cs.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEoMapper userEoMapper;
    @Autowired
    private RoleEoMapper roleEoMapper;

    @Override
    public void addUser(UserReqDto reqDto, UserEo currUser) {
        if (StringUtils.isBlank(reqDto.getUserCode())) {
            throw new RuntimeException("缺少参数");
        }
        UserEoExample userEoExample = new UserEoExample();
        userEoExample.createCriteria().andUserCodeEqualTo(reqDto.getUserCode());
        long count = userEoMapper.countByExample(userEoExample);
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }
        RoleEoExample roleEoExample = new RoleEoExample();
        roleEoExample.createCriteria().andParIdEqualTo(Long.valueOf(currUser.getRoleCode()));
        List<RoleEo> roleEoList = roleEoMapper.selectByExample(roleEoExample);
        if (CollectionUtils.isEmpty(roleEoList)) {
            throw new RuntimeException("当前用户不能添加用户");
        }
        UserEo userEo = new UserEo();
        BeanUtils.copyProperties(reqDto, userEo);
        userEo.setPwd("123456");
        userEo.setRelUser(currUser.getId());
        userEo.setRoleCode(roleEoList.get(0).getId().toString());
        userEo.setRoleName(roleEoList.get(0).getRoleName());
        userEo.setCreateTime(new Date());
        userEo.setUpdateTime(new Date());
        userEo.setCreatePerson(currUser.getId().toString());
        userEo.setUpdatePerson(currUser.getId().toString());
        userEoMapper.insert(userEo);
    }

    @Override
    public void updateUser(UserReqDto reqDto, UserEo currUser) {
        UserEo userEo = new UserEo();
        BeanUtils.copyProperties(reqDto, userEo);
        userEo.setUpdateTime(new Date());
        userEo.setUpdatePerson(currUser.getId().toString());
        userEoMapper.updateByPrimaryKeySelective(userEo);
    }

    @Override
    public void updateUserPwd(UserReqDto reqDto, UserEo currUser) {
        if (!currUser.getPwd().equals(reqDto.getOldPwd())) {
            throw new RuntimeException("旧密码不正确");
        }
        UserEo userEo = new UserEo();
        userEo.setId(currUser.getId());
        userEo.setPwd(reqDto.getNewPwd());
        userEo.setUpdateTime(new Date());
        userEoMapper.updateByPrimaryKeySelective(userEo);
    }

    @Override
    public void delUser(UserReqDto reqDto) {
        UserEoKey key = new UserEoKey();
        key.setId(reqDto.getId());
        userEoMapper.deleteByPrimaryKey(key);
    }

    @Override
    public PageResult<UserRespDto> page(UserReqDto reqDto, UserEo currUser) {
        UserEoExample eoExample = new UserEoExample();
        eoExample.createCriteria().andRelUserEqualTo(currUser.getId());
        //eoExample.createCriteria().andUserCodeEqualTo(reqDto.getUserCode());
        //eoExample.createCriteria().andUserNameLike(reqDto.getUserName());
        // eoExample.createCriteria().andEmailLike(reqDto.getUserEmail());
        long count = userEoMapper.countByExample(eoExample);
        PageResult<UserRespDto> pageResult = new PageResult<>();
        pageResult.setTotal(count);
        Integer pageNum = reqDto.getPageNum();
        Integer pageSize = reqDto.getPageSize();
        Integer num = null == pageNum ? WebConstants.DEFAULT_PAGE_NUMBER : pageNum;
        Integer size = null == pageSize ? WebConstants.DEFAULT_PAGE_SIZE : pageSize;
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        List<UserRespDto> list = new ArrayList<>();
        if (count > 0) {
            UserRespDto dto;
            PageHelper.startPage(num - 1, size);
            List<UserEo> userEoList = userEoMapper.selectByExample(eoExample);
            for (UserEo userEo : userEoList) {
                dto = new UserRespDto();
                BeanUtils.copyProperties(userEo, dto);
                list.add(dto);
            }
        }
        pageResult.setData(list);
        return pageResult;
    }
}
