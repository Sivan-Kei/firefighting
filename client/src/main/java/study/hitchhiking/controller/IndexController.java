package study.hitchhiking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import study.hitchhiking.VO.UserCenterVO;
import study.hitchhiking.clientUtils.UserUtils;
import study.hitchhiking.service.*;
import study.hitchhiking.utils.JWTUtil;

import study.hitchhiking.pojo.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ManageService manageService;
    @Autowired
    private CarService carService;

    @RequestMapping("/user")
    public String toIndex(HttpServletRequest request, Model model){
        User user = UserUtils.getUser(request,model,userService);

        model.addAttribute("one", new UserCenterVO(user, carService, ordersService));
        return "CIndex";
    }

    private String getUserID(HttpServletRequest request){
        String token = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if("token".equals(cookies[i].getName())){
                token = cookies[i].getValue();
            }
        }
        String userID = JWTUtil.getUIDByToken(token);//解码
        return userID;
    }
}
