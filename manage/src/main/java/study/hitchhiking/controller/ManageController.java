package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.hitchhiking.pojo.Manage;
import study.hitchhiking.service.CommentService;
import study.hitchhiking.service.ManageService;
import study.hitchhiking.service.OrdersService;

import java.math.BigDecimal;
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
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ManageService manageService;

    @RequestMapping("/select")
    public String getManage(@RequestParam(name = "target", required = false) String target,
                            @RequestParam(name = "typeOfSelect", required = false) String typeOfSelect,
                            @RequestParam(name = "role", required = false) String[] role, Model model) {
        QueryWrapper<Manage> wrapper = new QueryWrapper<>();
        //判断是否有查询条件
        if (null != role && role.length == 1) {
            wrapper.like("role", role[0]);
        }
        if (null != target && null != typeOfSelect) {
            wrapper.like(typeOfSelect, target);
        }
        //没有查询条件则查找全部，有条件则按照条件查询
        List<Manage> manageList = manageService.list(wrapper);
        model.addAttribute("manageList", manageList);
        return "manageSelect";
    }

    @RequestMapping("/insert")
    public String insertManage(@RequestParam(name = "orderID") String orderID,
                                @RequestParam(name = "userID") String userID,
                                @RequestParam(name = "role") String role,
                                @RequestParam(name = "initiatorID") String initiatorID,
                                @RequestParam(name = "currentprice") String currentprice,
                                @RequestParam(name = "requestedprice") String requestedprice,
                                Model model) {
        Manage manage = new Manage();
        manage.setUserID(Long.valueOf(userID));
        manage.setOrderID(Long.valueOf(orderID));
        manage.setInitiatorID(Long.valueOf(initiatorID));
        manage.setRole(role);
        try {
            manage.setCurrentprice(new BigDecimal(currentprice));
        } catch (Exception e) {
            manage.setCurrentprice(new BigDecimal(0));
        }
        try {
            manage.setRequestedprice(new BigDecimal(requestedprice));
        } catch (Exception e) {
            manage.setRequestedprice(new BigDecimal(0));
        }

        manageService.save(manage);

        model.addAttribute("manageList", manageService.list(null));
//        addUserVOList("userList", userService.list(null), model);
        return "manageSelect";
    }

    @RequestMapping("/delete")
    public String deleteManage(@RequestParam(name = "userID") String userID,
                                @RequestParam(name = "orderID") String orderID,
                                Model model) {
        QueryWrapper<Manage> manageQueryWrapper = new QueryWrapper<>();
        manageQueryWrapper.eq("userID",userID);
        manageQueryWrapper.eq("orderID",orderID);
        manageService.remove(manageQueryWrapper);
        model.addAttribute("manageList",manageService.list(null));
        return "manageSelect";
    }

    @RequestMapping("/details")
    public String getDetails(@RequestParam(name = "userID") String userID,
                             @RequestParam(name = "orderID") String orderID,
                             Model model) {
        QueryWrapper<Manage> manageQueryWrapper = new QueryWrapper<>();
        manageQueryWrapper.eq("userID",userID);
        manageQueryWrapper.eq("orderID",orderID);
        model.addAttribute("one",manageService.getOne(manageQueryWrapper));
        return "manage";
    }

    @RequestMapping("/update")
    public String updateManage(@RequestParam(name = "initiatorID") String initiatorID,
                               @RequestParam(name = "orderID") String orderID,
                               @RequestParam(name = "userID") String userID,
                               @RequestParam(name = "role") String role,
                               @RequestParam(name = "currentprice") String currentprice,
                               @RequestParam(name = "requestedprice") String requestedprice,
                               Model model) {
        QueryWrapper<Manage> manageQueryWrapper = new QueryWrapper<>();
        manageQueryWrapper.eq("userID",userID);
        manageQueryWrapper.eq("orderID",orderID);
        Manage manage = manageService.getOne(manageQueryWrapper);
        if(!"不修改".equals(role)){
            manage.setRole(role);
        }

        try {
            manage.setCurrentprice(new BigDecimal(currentprice));
        } catch (Exception e) {
            manage.setCurrentprice(new BigDecimal(0));
        }
        try {
            manage.setRequestedprice(new BigDecimal(requestedprice));
        } catch (Exception e) {
            manage.setRequestedprice(new BigDecimal(0));
        }

        manageService.update(manage,manageQueryWrapper);

        model.addAttribute("manageList", manageService.list(null));
        //addUserVOList("userList", userService.list(null), model);
        return "manageSelect";
    }

}

