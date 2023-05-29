package study.fire_fighting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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
    public ResponseData getUsers() {
        ResponseData res = new ResponseData();
        System.out.println(userService);
        res.put("userList",userService.list(null));
        return res;
    }
}

