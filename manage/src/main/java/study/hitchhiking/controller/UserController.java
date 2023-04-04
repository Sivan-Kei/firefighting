package study.hitchhiking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.UserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴建豪
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/all")
    public List<User> get(){
        return  userService.list(null);
    }


}

