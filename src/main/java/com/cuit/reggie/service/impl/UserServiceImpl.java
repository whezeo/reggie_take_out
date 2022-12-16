package com.cuit.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.reggie.pojo.User;
import com.cuit.reggie.service.UserService;
import com.cuit.reggie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author liveb
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2022-12-15 13:41:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




