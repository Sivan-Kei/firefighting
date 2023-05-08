package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.hitchhiking.VO.CommentVO;
import study.hitchhiking.VO.UserCenterVO;
import study.hitchhiking.clientUtils.UserUtils;
import study.hitchhiking.pojo.Comment;
import study.hitchhiking.pojo.Orders;
import study.hitchhiking.service.CarService;
import study.hitchhiking.service.CommentService;
import study.hitchhiking.service.OrdersService;
import study.hitchhiking.service.UserService;

import javax.servlet.http.HttpServletRequest;

import study.hitchhiking.pojo.User;

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
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @RequestMapping("/insert")
    public String addComment(@RequestParam(name="orderID") String orderID,
                             @RequestParam(name="comment") String comment,
                             HttpServletRequest request, Model model){
        User user = UserUtils.getUser(request,model,userService);
        Comment saveOne = new Comment();
        saveOne.setOrderID(Long.valueOf(orderID));
        saveOne.setUserID(user.getUserID());
        saveOne.setContent(comment);
        saveOne.setRole(ordersService.getById(orderID).getRole());
        saveOne.setCommenttime(new Date());
        commentService.save(saveOne);
        model.addAttribute("one", new UserCenterVO(user, carService,ordersService));
        return "userCenter";
    }

    @RequestMapping("/select")
    public String getComment(HttpServletRequest request, Model model) {
        User user = UserUtils.getUser(request,model,userService);
        List<Comment> commentList = new ArrayList<>();
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("userID",user.getUserID());
        commentList.addAll(commentService.list(wrapper));

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("userID",user.getUserID());
        List<Orders> ordersList = ordersService.list(ordersQueryWrapper);
        List<String> orderIdList = new ArrayList<>();
        for (Orders orders : ordersList) {
            orderIdList.add(orders.getOrderID().toString());
        }
        wrapper = new QueryWrapper<>();
        wrapper.in("orderID",orderIdList);
        model.addAttribute("commentList", commentVOList(commentList));
        return "commentList";
    }

    private List<CommentVO> commentVOList(List<Comment> list){
        if(null == list || list.size()==0)return new ArrayList<>();
        List<CommentVO> voList = new ArrayList<>();
        for (Comment comment : list) {
            voList.add(new CommentVO(comment));
        }
        return voList;
    }
}

