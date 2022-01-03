package com.springsecury;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JwtTest
 * 个人博客: https://yangbuyi.top
 * @author yangbuyiya
 * @Date: 2021-03-25 10:05
 * @Description: $
 **/
public class JwtTest {

    /**
     * 加密jwt
     *
     * @param username
     * @return
     */
    public static String createJwt(String username) {
        // 颁发时间
        Date createTime = new Date();
        // 过期时间
        Calendar now = Calendar.getInstance();
        // 设置未来时间 秒
        now.set(Calendar.SECOND, 7200);
        Date expireTime = now.getTime();
        // header
        Map<String, Object> header = new HashMap<>(4);
        header.put("alg", "HS256");
        header.put("type", "JWT");
        // 载体
        return JWT.create()
                // 设置头部信息
                .withHeader(header)
                // 设置创建时间
                .withIssuedAt(createTime)
                // 设置过期时间
                .withExpiresAt(expireTime)
                // 设置主体
                .withSubject("这是一个JWT")
                // 设置载荷--也就是用户信息
                .withClaim("username", username)
                .withClaim("pwd", "123456")
                // 设置签名密钥
                .sign(Algorithm.HMAC256("yby-jwt"));
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     */
    public static boolean decryptJwt(String jwt) {
        // 带入密钥解密
        JWTVerifier require = JWT.require(Algorithm.HMAC256("yby-jwt")).build();
        try {
            DecodedJWT verify = require.verify(jwt);
            // 根据设置的key获取对应的value
            Claim username = verify.getClaim("username");
            System.out.println(username.asString());
            System.out.println(verify.getSignature());
            System.out.println(verify.getSubject());
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // 创建令牌
        String jwt = createJwt("杨不易");
        System.out.println(jwt);
        decryptJwt(jwt);

        // 解析令牌       System.out.println(decryptJwt("eyJ0eXAiOiJKV1QiLCJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiLov5nmmK_kuIDkuKpKV1QiLCJleHAiOjE2MTcwODAyMjAsInB3ZCI6IjEyMzQ1NiIsImlhdCI6MTYxNzA3MzAzMSwidXNlcm5hbWUiOiLmnajkuI3mmJMifQ.GJOeFSVsAwFPcgUlalmxVXt0QQ-Be5bhGUtL1ep04vM"));
    }
}

