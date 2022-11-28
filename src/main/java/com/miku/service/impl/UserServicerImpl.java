package com.miku.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miku.entity.User;
import com.miku.mapper.UserMapper;

import com.miku.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServicerImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
