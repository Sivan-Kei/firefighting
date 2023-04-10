package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import study.hitchhiking.VO.userVO;
import study.hitchhiking.pojo.Car;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.CarService;
import study.hitchhiking.service.UserService;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CarService carService;

    //@ResponseBody
    @RequestMapping("/all")
    public String getAll(Model model){
        //model.addAttribute("userList",userService.list(null));
        addUserVOList("userList",userService.list(null),model);
        return  "userSelect";
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
        //model.addAttribute("userList",userList);
        addUserVOList("userList",userList,model);
        return "userSelect";
    }

    @RequestMapping("/details")
    public String userDetails(@RequestParam(name="userID") String userID,
            @RequestParam(name="change",required = false) String change, Model model){
        User user = userService.getById(userID);
        model.addAttribute("one",new userVO(user,carService));

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("userID",userID);
        model.addAttribute("cars",carService.list(null));
        if("change".equals(change)){
            return "userUpdate";
        }
        return "user";
    }

    @RequestMapping("/update")
    public String updateUser(@RequestParam(name="userID") String userID,
                             @RequestParam(name="name") String name,
                             @RequestParam(name="sexChange") String sexChange,
                             @RequestParam(name="phonenumber") String phonenumber,
                             @RequestParam(name="password") String password,
                             @RequestParam(name="identification") String identification,
                             Model model){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("userID",userID);

        User user =userService.getOne(wrapper);
        user.setName(name);
        if("true".equals(sexChange)){
            user.setSex("男".equals(user.getSex())?"女":"男");
        }
        user.setPassword(password);
        user.setPhonenumber(phonenumber);
        user.setIdentification(identification);

        userService.updateById(user);


        model.addAttribute("one",new userVO(user,carService));

        QueryWrapper<Car> carWrapper = new QueryWrapper<>();
        carWrapper.eq("userID",userID);
        model.addAttribute("cars",carService.list(null));
        return "user";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam(name="userID") String userID, Model model){
        userService.removeById(Long.valueOf(userID));
        //System.out.println("userID="+userID);
        addUserVOList("userList",userService.list(null),model);
        return "userSelect";
    }

    @RequestMapping("/insert")
    public String insertUser(@RequestParam(name="name") String name,
                             @RequestParam(name="sex") String sex,
                             @RequestParam(name="phonenumber") String phonenumber,
                             @RequestParam(name="password") String password,
                             @RequestParam(name="identification") String identification,
                             Model model){
        if(null == phonenumber || null == password){
            addUserVOList("userList",userService.list(null),model);
            return "userSelect";
        }
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        user.setPassword(password);
        user.setPhonenumber(phonenumber);
        user.setIdentification(identification);
        user.setCreatetime(new Date(System.currentTimeMillis()));
        userService.save(user);
        //model.addAttribute("userList",userService.list(null));
        addUserVOList("userList",userService.list(null),model);
        return "userSelect";
    }

    private void addUserVOList(String name,List<User> list,Model model){
        List<userVO> VOList = new ArrayList<>();
        for (User user : list) {
            VOList.add(new userVO(user,carService));
        }
        model.addAttribute(name,VOList);
    }

}

