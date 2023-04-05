package study.hitchhiking.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.hitchhiking.pojo.User;
import study.hitchhiking.service.UserService;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Before
    @Rollback
    public void insert_should_success(){
        User user=new User();
        user.setName("fxy");

        boolean flag = userService.save(user);
        Assertions.assertTrue(flag);
    }

    @Test
    public void select_should_success(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.like("name","fxy").last("LiMIT 1");

        User user = userService.getOne(wrapper);
        Assertions.assertNotEquals(null,user);
        Assertions.assertEquals("fxy",user.getName());
    }
}
