package study.hitchhiking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.hitchhiking.service.CarService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 吴建豪
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;


}

