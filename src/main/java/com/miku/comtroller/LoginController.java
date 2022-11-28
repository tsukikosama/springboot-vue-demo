package com.miku.comtroller;

import cn.hutool.core.bean.BeanUtil;
import com.miku.common.Result;
import com.miku.entity.User;
import com.miku.service.impl.UserServicerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("login")
@Slf4j
@RestController
//@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600)
public class LoginController {
    @Autowired
    private UserServicerImpl servicer;

    @PostMapping
    public Result login(@RequestBody User user){
        System.out.println(user);
        if(user == null){
            return Result.fail("用户名为空");
        }
        User users = servicer.query().eq("username", user.getUsername()).one();
        //判断user是否为空  如果为空就添加进数据库
        if(users == null){
//          User u = new User()
            User user1 = new User();
            BeanUtil.copyProperties(user,user1);
            log.info("添加成功");
            servicer.save(user1);
        }
        //判断密码是否相同  如果密码不同就登录失败
        if(!user.getPassword().equals(users.getPassword())){
            return Result.fail("用户名或者密码错误");
        }
        //不为空就返回user用户结果
        return Result.ok(user);
    }
}
