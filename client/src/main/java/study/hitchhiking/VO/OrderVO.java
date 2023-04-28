package study.hitchhiking.VO;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Getter;
import study.hitchhiking.pojo.Car;
import study.hitchhiking.pojo.Manage;
import study.hitchhiking.pojo.Orders;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.CarService;
import study.hitchhiking.service.ManageService;
import study.hitchhiking.service.UserService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderID;

    private String orderstatus;

    private BigDecimal orderprice;

    private String carID;

    private String getonposition;

    private String getontime;

    private String getoffposition;

    private String threshold;

    private String destination;

    private String departtime;
    public static final String DEFAULT_DATE = "****-**-** **:**:**";

    private String initiatorName;
    private String initiatorID;
    private String initiatorRole;
    private String askForPrice;
    private String FROM_TO;
    private String startTime;
    private String phone;

    private String seat;


    public OrderVO(Orders order, UserService userService, CarService carService) {
        this.orderID = order.getOrderID();
        this.orderstatus = order.getOrderstatus();
        this.orderprice = order.getOrderprice();
        this.carID = order.getCarID();
        this.getonposition = order.getGetonposition();
        this.getoffposition = order.getGetoffposition();
        this.threshold = order.getThreshold();
        this.destination = order.getDestination();
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        if (null != order.getDeparttime()) {
            departtime = dateFormat.format(order.getDeparttime());
        } else {
            departtime = DEFAULT_DATE;
        }
        if (null != order.getGetontime()) {
            getontime = dateFormat.format(order.getGetontime());
        } else {
            getontime = DEFAULT_DATE;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        initiatorID = order.getUserID().toString();
        initiatorRole = order.getRole();
        askForPrice = String.format("%.2f", order.getOrderprice());
        if ("乘客".equals(initiatorRole)) {
            FROM_TO = order.getGetonposition() + " —— " + order.getGetoffposition();
            startTime = dateFormat.format(order.getGetontime());
            seat = " ";
        } else if ("司机".equals(initiatorRole)) {
            FROM_TO = order.getThreshold() + " —— " + order.getDestination();
            startTime = dateFormat.format(order.getDeparttime());

            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("carID", order.getCarID());
            seat = "座位数：" + carService.getOne(carQueryWrapper).getSeatnumber().toString();
        }
        userQueryWrapper.eq("userID", order.getUserID());
        User user = userService.getOne(userQueryWrapper);
        phone = user.getPhonenumber();
        initiatorName = user.getName();
    }

    public OrderVO(Orders order, User user, CarService carService) {
        this.orderID = order.getOrderID();
        this.orderstatus = order.getOrderstatus();
        this.orderprice = order.getOrderprice();
        this.carID = order.getCarID();
        this.getonposition = order.getGetonposition();
        this.getoffposition = order.getGetoffposition();
        this.threshold = order.getThreshold();
        this.destination = order.getDestination();
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        if (null != order.getDeparttime()) {
            departtime = dateFormat.format(order.getDeparttime());
        } else {
            departtime = DEFAULT_DATE;
        }
        if (null != order.getGetontime()) {
            getontime = dateFormat.format(order.getGetontime());
        } else {
            getontime = DEFAULT_DATE;
        }

        initiatorID = user.getUserID().toString();
        initiatorRole = order.getRole();
        askForPrice = String.format("%.2f", order.getOrderprice());
        if ("乘客".equals(initiatorRole)) {
            FROM_TO = order.getGetonposition() + " —— " + order.getGetoffposition();
            startTime = dateFormat.format(order.getGetontime());
            seat = " ";
        } else if ("司机".equals(initiatorRole)) {
            FROM_TO = order.getThreshold() + " —— " + order.getDestination();
            startTime = dateFormat.format(order.getDeparttime());

            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("carID", order.getCarID());
            seat = "座位数：" + carService.getOne(carQueryWrapper).getSeatnumber().toString();
        }
        phone = user.getPhonenumber();
        initiatorName = user.getName();
    }
}