package study.fire_fighting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import study.fire_fighting.pojo.User;
import study.fire_fighting.utils.JWTUtil;
import study.fire_fighting.utils.response.ResponseData;
import study.fire_fighting.service.UserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ksw
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/select")
    public ResponseData getUsers(int code) {
        ResponseData res = new ResponseData();
        System.out.println(userService);
        res.put("userList",userService.list(null));
        if(code==0){
            return new ResponseData();
        }
        return res;
    }

    @RequestMapping("/login")
    public ResponseData login(String uid,String upassword) {

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("uid",uid);
        wrapper.eq("upassword",upassword);
        User user = userService.getOne(wrapper);

        if(null == user){
            return ResponseData.failed();
        }

        ResponseData res = new ResponseData();
        res.put("userMsg", JWTUtil.signWithUserMsg(uid,upassword));
        return res;
    }

    @RequestMapping("/register")
    public ResponseData register(String uid, String upassword, String utel) {

        // 检查用户是否已存在
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("uid", uid);
        User existingUser = userService.getOne(wrapper);
        if (existingUser != null) {
            return ResponseData.failed("用户已存在");
        }

        // 创建新用户
        User newUser = new User();
        newUser.setUid(Long.valueOf(uid));
        newUser.setUpassword(upassword);
        newUser.setUtel(utel);
        // 设置其他注册时需要的属性

        // 保存用户信息到数据库
        boolean saved = userService.save(newUser);
        if (!saved) {
            return ResponseData.failed("注册失败");
        }

        // 注册成功，返回成功消息或其他需要的数据
        ResponseData res = new ResponseData();
        res.put("message", "注册成功");
        return res;
    }
}

