package com.wcx.excelword.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenUtil {
    private static Long tokenExpireTime = 1000 * 60 * 30L;//  单位毫秒
    public static final String PRIVATEKEY = "privateKey";
    public static final String ACCESSTOKEN = "AccessToken";// 公私钥
    private static final String secretKey = "ueor82739sjsd234759jdfijosd289347sdjklfvjsxdr389wrksjdhfjksdyr9234yu89htsdkhfjksdhf83wy4hrsdjkhfsdjkh8i34wyuirfhsdjkfsxmnfbcvm,xcnskdhfriw3eyrikni12y391y238923y4y89dfhisfhsdjknfk23w4y598hfdjkfkjxcfbnisyer93we5rhkdjsfnjks"; // secret

    // 生成token
    public static String createToken(String userName){
        // 得到当前时间
        Date now = new Date();
        // 通过hs256算法，以及secretKey得到Algorithm对象
        Algorithm algo = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("qianfeng")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                .withClaim("userName", userName)//保存身份标识
                .sign(algo);
        return token;
    }



    /**
     * JWT验证
     * @param token
     * @return userName
     */
    public static String verifyJWT(String token){
        String userName = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("qianfeng")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            userName = jwt.getClaim("userName").asString();
        } catch (JWTVerificationException e){
            e.printStackTrace();
        }
        return userName;
    }

    public static void main(String[] args) {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJxaWFuZmVuZyIsImV4cCI6MTY1NDY3MzY4OCwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTY1NDY3MTg4OH0.CxTIfu58LDeVtWEuHRfdO8mmA9J_1gtL5Go9KloHu30";
        System.out.println(verifyJWT(jwt));

//        System.out.println(createToken("admin"));
    }
}
