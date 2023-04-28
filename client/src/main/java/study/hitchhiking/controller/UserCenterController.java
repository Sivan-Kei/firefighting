package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.hitchhiking.VO.UserCenterVO;
import study.hitchhiking.clientUtils.UserUtils;
import study.hitchhiking.pojo.Car;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.*;
import study.hitchhiking.utils.JWTUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴建豪
 * @since 2023-04-05
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController {
    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ManageService manageService;

    @RequestMapping("/details")
    public String userCenter(HttpServletRequest request,Model model) {
        User user = UserUtils.getUser(request,model,userService);
        model.addAttribute("one", new UserCenterVO(user, carService,ordersService));
        return "userCenter";
    }

    @RequestMapping("/update")
    public String updateUser(@RequestParam(name = "name",required = false) String name,
                             @RequestParam(name = "sexChange",required = false) String sexChange,
                             @RequestParam(name = "phonenumber",required = false) String phonenumber,
                             @RequestParam(name = "password",required = false) String password,
                             @RequestParam(name = "identification",required = false) String identification,
                             HttpServletRequest request,
                             Model model) {
        User user = UserUtils.getUser(request,model,userService);
        user.setName(name);
        if ("true".equals(sexChange)) {
            user.setSex("男".equals(user.getSex()) ? "女" : "男");
        }
        user.setPassword(password);
        user.setPhonenumber(phonenumber);
        user.setIdentification(identification);

        userService.updateById(user);


        model.addAttribute("one", new UserCenterVO(user, carService,ordersService));
        return "updateUser";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam(name = "userID") String userID, Model model) {
        userService.removeById(Long.valueOf(userID));
        return "redirect:/CLogin.html";
    }
}


