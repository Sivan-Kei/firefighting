package study.hitchhiking.controller;

import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import study.hitchhiking.config.CONFIG;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.UserService;
import study.hitchhiking.utils.JWTUtil;
import study.hitchhiking.utils.response.ResponseData;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/user")
    public ResponseData userLogin(@RequestParam(value = "phonenumber", required = true) String phonenumber,
                                  @RequestParam(value = "password", required = true) String password) {
        if(!CONFIG.ADMIN_NUMBER.equals(phonenumber)){
            return ResponseData.failed(-1,"非管理员账号");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("phonenumber", phonenumber).last("LiMIT 1");
        User user = userService.getOne(wrapper);
        System.out.println(user.getPassword());
        String userToken;
        ResponseData responseData = new ResponseData();
        if (null != user && null != user.getPassword() && user.getPassword().equals(password)) {
            userToken = JWTUtil.signWithUID(user.getUserID().toString());
            responseData.put("token", userToken);
            System.out.println(phonenumber + " login");
        }
        return responseData;
    }
}
