package study.hitchhiking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import study.hitchhiking.service.UserService;
import study.hitchhiking.utils.JWTUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String toIndex(HttpServletRequest request, Model model){
        String userID = getUserID(request);
        model.addAttribute("UserID",userID);
        model.addAttribute("UserName",userService.getById(userID).getName());
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
