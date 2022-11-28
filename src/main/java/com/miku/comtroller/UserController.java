package com.miku.comtroller;

import com.miku.common.Result;
import com.miku.entity.User;
import com.miku.service.impl.UserServicerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServicerImpl servicer;
    @GetMapping
    public Result getUsers(){
        return Result.ok(servicer.list());
    }
    @PostMapping("/updata")
    public Result updata(@RequestBody User user){
        System.out.println(user);
        boolean success = servicer.update().eq("uid", user.getUid()).update(user);
        if(!success){
            return Result.fail("添加失败");
        }
        return Result.ok(user);
    }
    @DeleteMapping("/deleteuser")
    public Result delete(@RequestParam("uid") Long uid){
        boolean success = servicer.removeById(Long.valueOf(uid));
        if (!success){
            return Result.fail("删除失败");
        }
        return Result.ok("成功");
    }
}
