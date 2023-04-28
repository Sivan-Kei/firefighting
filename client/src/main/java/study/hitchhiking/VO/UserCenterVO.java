package study.hitchhiking.VO;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Getter;
import study.hitchhiking.pojo.Car;
import study.hitchhiking.pojo.Orders;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.CarService;
import study.hitchhiking.service.OrdersService;
import study.hitchhiking.service.UserService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserCenterVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userID;

    private String password;

    private String name;

    private String sex;

    private String phonenumber;

    private String identification;

    private String createtime;

    private List<Car> car;

    private List<OrderVO> orders;

    private static final String DEFAULT_DATE = "0000-00-00 00:00:00";
    public UserCenterVO(User user){
        this.userID = user.getUserID();
        this.password = user.getPassword();
        this.name = user.getName();
        this.identification = user.getIdentification();
        this.phonenumber = user.getPhonenumber();
        this.sex = user.getSex();
        if(null != user.getCreatetime()){
            createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreatetime());
        }else{
            createtime = DEFAULT_DATE;
        }
    }

    public UserCenterVO(User user, CarService carService, OrdersService ordersService){
        this.userID = user.getUserID();
        this.password = user.getPassword();
        this.name = user.getName();
        this.identification = user.getIdentification();
        this.phonenumber = user.getPhonenumber();
        this.sex = user.getSex();
        if(null != user.getCreatetime()){
            createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreatetime());
        }else{
            createtime = DEFAULT_DATE;
        }

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("userID",userID);
        car = carService.list(wrapper);

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<>();
        ordersQueryWrapper.eq("userID",userID);
        List<Orders> ordersList = ordersService.list(ordersQueryWrapper);
        orders = new ArrayList<>();
        for (Orders order : ordersList) {
            orders.add(new OrderVO(order,user,carService));
        }
    }

    public int getCarNumber(){
        return car.size();
    }

}
