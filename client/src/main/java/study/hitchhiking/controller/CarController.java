package study.hitchhiking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.hitchhiking.VO.UserCenterVO;
import study.hitchhiking.clientUtils.UserUtils;
import study.hitchhiking.pojo.Car;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.CarService;
import study.hitchhiking.service.OrdersService;
import study.hitchhiking.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴建豪
 * @since 2023-04-10
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrdersService ordersService;


    @RequestMapping("/update")
    public String updateCar(@RequestParam(name = "carID") String carID,
                            @RequestParam(name = "seatnumber") String seatnumber,
                            @RequestParam(name = "platenumber") String platenumber,
                            @RequestParam(name = "fuelconsumption") String fuelconsumption,
                            @RequestParam(name = "oiltype") String oiltype,
                            HttpServletRequest request,
                            Model model) {
        User user = UserUtils.getUser(request,model,userService);
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("carID", carID);

        Car car = carService.getOne(wrapper);
        car.setSeatnumber(Integer.valueOf(seatnumber));
        car.setPlatenumber(platenumber);
        car.setFuelconsumption(new BigDecimal(fuelconsumption));
        car.setOiltype(oiltype);
        carService.updateById(car);


        model.addAttribute("one", new UserCenterVO(userService.getById(car.getUserID()), carService,ordersService));

        wrapper = new QueryWrapper<>();
        wrapper.eq("userID", car.getUserID());
        model.addAttribute("cars", carService.list(wrapper));
        return "user";
    }

    @RequestMapping("/details")
    public String userDetails(@RequestParam(name = "carID") String carID,
                              HttpServletRequest request, Model model) {
        Car car = carService.getById(carID);
        model.addAttribute("one", car);
        return "updateCar";
    }

    @RequestMapping("/delete")
    public String deleteCar(@RequestParam(name = "carID") String carID,
                            HttpServletRequest request,Model model) {
        User user = UserUtils.getUser(request,model,userService);
        carService.removeById(Long.valueOf(carID));
        //System.out.println("userID="+userID);

        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("carID", carID);

        Car car = carService.getOne(wrapper);
        model.addAttribute("one", new UserCenterVO(userService.getById(car.getUserID()), carService,ordersService));

        wrapper = new QueryWrapper<>();
        wrapper.eq("userID", car.getUserID());
        model.addAttribute("cars", carService.list(wrapper));

        return "user";
    }

    @RequestMapping("/insert")
    public String insertCar(@RequestParam(name = "userID") String userID,
                            @RequestParam(name = "seatnumber") String seatnumber,
                            @RequestParam(name = "platenumber") String platenumber,
                            @RequestParam(name = "fuelconsumption") String fuelconsumption,
                            @RequestParam(name = "oiltype") String oiltype,
                            HttpServletRequest request,
                            Model model) {
        User user = UserUtils.getUser(request,model,userService);
        Car car = new Car();
        car.setUserID(Long.valueOf(userID));
        car.setSeatnumber(Integer.valueOf(seatnumber));
        car.setPlatenumber(platenumber);
        car.setFuelconsumption(new BigDecimal(fuelconsumption));
        car.setOiltype(oiltype);
        carService.save(car);

        model.addAttribute("one", new UserCenterVO(userService.getById(userID), carService,ordersService));


        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("userID", userID);
        model.addAttribute("cars", carService.list(wrapper));
        return "user";
    }
}

