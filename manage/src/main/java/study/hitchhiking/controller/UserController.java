package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //@ResponseBody
    @RequestMapping("/all")
    public String getAll(Model model){
        model.addAttribute("userList",userService.list(null));
        return  "index";
    }

    @RequestMapping("/select")
    public String getUsers(@RequestParam(name="target",required = false) String target,
            @RequestParam(name="typeOfSelect",required = false) String typeOfSelect,
            @RequestParam(name="sex",required = false) String[] sex, Model model){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //判断是否有查询条件
        if(null != sex && sex.length==1){
            wrapper.like("sex",sex[0]);
        }
        if(null != target && null != typeOfSelect){
            wrapper.like(typeOfSelect,target);
        }
        //没有查询条件则查找全部，有条件则按照条件查询
        List<User> userList = userService.list(wrapper);
        model.addAttribute("userList",userList);
        return "userSelect";
    }

    @RequestMapping("/update")
    public String updateUser(Model model){


        return "user";
    }

}

