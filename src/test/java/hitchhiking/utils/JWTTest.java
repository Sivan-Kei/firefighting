package hitchhiking.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.hitchhiking.utils.JWTUtil;

@SpringBootTest
public class JWTTest {
    @Test
    public void should_sign_success(){
        String testTarget = "123";
        String token = JWTUtil.signWithUID(testTarget);
        Assertions.assertNotEquals(testTarget,token);
        String unSignTarget = JWTUtil.getUIDByToken(token);
        Assertions.assertEquals(testTarget,unSignTarget);
    }
}
