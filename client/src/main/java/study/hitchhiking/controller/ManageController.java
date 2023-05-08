package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.hitchhiking.clientUtils.UserUtils;
import study.hitchhiking.pojo.Manage;
import study.hitchhiking.pojo.Orders;
import study.hitchhiking.service.ManageService;

import javax.servlet.http.HttpServletRequest;

import study.hitchhiking.pojo.User;
import study.hitchhiking.service.OrdersService;
import study.hitchhiking.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
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
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;
    @Autowired
    private UserService userService;

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/argue")
    public String argue(@RequestParam(name="orderID") String orderID,
                        @RequestParam(name="price") int price,
            HttpServletRequest request, Model model){
        User user = UserUtils.getUser(request,model,userService);
        QueryWrapper<Manage> wrapper = new QueryWrapper<>();
        wrapper.eq("orderID",orderID);
        wrapper.eq("userID",user.getUserID());
        Manage manage = manageService.getOne(wrapper);
        if(null == manage){
            manage = new Manage();
            manage.setUserID(user.getUserID());
            manage.setOrderID(Long.valueOf(orderID));
            manage.setCurrentprice(ordersService.getById(orderID).getOrderprice());
            manage.setRequestedprice(new BigDecimal(price));
            manageService.save(manage);
        }else{
            manage.setRequestedprice(new BigDecimal(price));
            manageService.update(manage,wrapper);
        }
        return "redirect:/manage/select";
    }

    @RequestMapping("/select")
    public String getManage(HttpServletRequest request,Model model) {
        User user = UserUtils.getUser(request,model,userService);
        QueryWrapper<Manage> wrapper = new QueryWrapper<>();
        wrapper.eq("userID",user.getUserID());
        //没有查询条件则查找全部，有条件则按照条件查询
        List<Manage> manageList = manageService.list(wrapper);
        model.addAttribute("manageList", manageList);

        wrapper = new QueryWrapper<>();
        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("userID",user.getUserID());
        List<Orders> ordersList = ordersService.list(ordersQueryWrapper);
        List<String> orderIDs = new ArrayList<>();
        for (Orders orders : ordersList) {
            orderIDs.add(orders.getUserID().toString());
        }
        wrapper.in("orderID",orderIDs);
        //没有查询条件则查找全部，有条件则按照条件查询
        List<Manage> managedList = manageService.list(wrapper);
        model.addAttribute("managedList", managedList);
        return "manageList";
    }

    @RequestMapping("/agree")
    public String agree(@RequestParam(name="orderID") String orderID,
                        @RequestParam(name="userID") String userID,
                        HttpServletRequest request,Model model) {
        User user = UserUtils.getUser(request,model,userService);
        QueryWrapper<Manage> wrapper = new QueryWrapper<>();
        wrapper.eq("orderID",orderID);
        wrapper.eq("userID",userID);
        Manage manage = manageService.getOne(wrapper);

        wrapper = new QueryWrapper<>();
        wrapper.eq("orderID",orderID);
        manageService.remove(wrapper);

        Orders orders = ordersService.getById(orderID);
        orders.setOrderstatus("行程中");
        orders.setOrderprice(manage.getRequestedprice());
        ordersService.save(orders);

        return "redirect:/manage/select";
    }
}

