package study.hitchhiking.clientUtils;

import org.springframework.ui.Model;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.UserService;
import study.hitchhiking.utils.JWTUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class UserUtils {
    public static User getUser(HttpServletRequest request, Model model, UserService userService){
        String token = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if("token".equals(cookies[i].getName())){
                token = cookies[i].getValue();
            }
        }
        String userID = JWTUtil.getUIDByToken(token);//解码
        User user = userService.getById(userID);
        model.addAttribute("UserName",user.getName());
        return user;
    }
}
