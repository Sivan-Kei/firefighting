package study.hitchhiking.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.Date;
import java.util.Map;

public class JWTUtil {
    private static final long EXPIRE_TIME = 1800000L;
    private static final String SECRET = "ffaagoicgioIUIUOAOUI(!23454KLJTADW:??{}{|】]l'ad,'q[5fdf>?N<:{LWPW";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    public static String signWithUID(String userID){
        return JWT.create().withClaim("userID",userID)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .sign(ALGORITHM);
    }


    public static String getUIDByToken(String token){
        DecodedJWT jwt = JWT.decode(token);
        if(jwt.getExpiresAt().compareTo(new Date(System.currentTimeMillis()))>0) {
            return null;
        } else {
            return jwt.getClaim("userID").asString();
        }
    }
}
