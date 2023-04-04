package study.hitchhiking.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.hitchhiking.service.UserService;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void insert_should_success(){

    }
}
