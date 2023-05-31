package study.fire_fighting.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.Date;

public class JWTUtil {
    //token过期时间 EXPIRE_TIME ms
    private static final long EXPIRE_TIME = 60*60*1000L;//60min
    private static final String SECRET = "ffaagoicgioIUIUOAOUI(!23454KLJTADW:??{}{|】]l'ad,'q[5fdf>?N<:{LWPW";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    public static String signWithUserMsg(String uid,String upassword){
        return JWT.create().withClaim("userMsg",uid+":"+upassword)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .sign(ALGORITHM);
    }


    public static String getUserMsgByToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        if(jwt.getExpiresAt().compareTo(new Date(System.currentTimeMillis()))<=0) {
            return null;
        } else {
            return jwt.getClaim("userMsg").asString();
        }
    }

    public static String getUserIDByToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        if(jwt.getExpiresAt().compareTo(new Date(System.currentTimeMillis()))<=0) {
            return null;
        } else {
            //token拼接格式
            //uid：upassword
            return jwt.getClaim("userMsg").asString().split(":")[0];
        }
    }
}
