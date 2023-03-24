package study.hitchhiking.service.impl;

import study.hitchhiking.pojo.User;
import study.hitchhiking.mapper.UserMapper;
import study.hitchhiking.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 吴建豪
 * @since 2023-03-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
