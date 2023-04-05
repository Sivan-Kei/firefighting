package study.hitchhiking.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTTest {
    @Test
    public void should_sign_success(){
        String testTarget = "123";

        String token = JWTUtil.signWithUID(testTarget);
        Assertions.assertNotEquals(testTarget,token);
        //格式检验
        int count = 0;
        for (int i = 0; i < token.length(); i++) {
            if(token.charAt(i) == '.'){
                count ++;
            }
        }
        Assertions.assertEquals(2,count);
        String unSignTarget = JWTUtil.getUIDByToken(token);
        Assertions.assertEquals(testTarget,unSignTarget);
    }

    @Test
    public void test(){
        String unSignTarget = JWTUtil.getUIDByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODA2MDI2NDEsInVzZXJJRCI6IjEifQ.L9swvHSv7l633GdwvaAUya75bwiekhZVkuIrwbYjoYw");
        System.out.println(unSignTarget);
    }
}
