package study.hitchhiking.service.impl;

import study.hitchhiking.pojo.Car;
import study.hitchhiking.mapper.CarMapper;
import study.hitchhiking.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2023-03-23
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
