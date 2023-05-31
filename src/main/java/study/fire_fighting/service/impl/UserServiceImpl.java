package study.fire_fighting.service.impl;

import study.fire_fighting.pojo.User;
import study.fire_fighting.mapper.UserMapper;
import study.fire_fighting.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ksw
 * @since 2023-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
