package study.hitchhiking.service.impl;

import study.hitchhiking.pojo.Orders;
import study.hitchhiking.mapper.OrdersMapper;
import study.hitchhiking.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴建豪
 * @since 2023-04-05
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
