package study.fire_fighting.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import study.fire_fighting.AutoApplication;
import study.fire_fighting.service.UserService;
import study.fire_fighting.utils.response.ResponseData;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoApplication.class)
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Before
    public void before(){}

    @Test
    public void should_select_user_success(){
        ResponseData res = new ResponseData();
        res.put("userList",userService.list(null));
        Assert.assertEquals(0,((ArrayList)res.get("userList")).size());
    }

}
