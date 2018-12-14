package com.sailing.springbootmybatis.service.impl;

import com.sailing.springbootmybatis.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service.impl
 * @Description: token 接口实现类
 * @date 2018/12/6 15：42
 */
@Service
public class TokenServiceImpl implements TokenService{

    //// 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
    @Value("${jjwt.secret.key}")
    private String primaryKey;

    @Override
    public String createJWT(String id, String sub, long millionSeconds) {
        SecretKey key = generalKey();
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Claims claims = Jwts.claims();
        claims.put("user_name", "白冰");
        claims.put("roles", "管理员");
        claims.put("login_date", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now));
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)//使用自定义载体，这个需要放到最前面，否则会将下面的设置覆盖掉
                .setId(id)//token的id（代表唯一性)
                .setIssuer("sailing")//签发人
                .setSubject(sub)//主题
                .setAudience("web_client")//接收jwt的一方
                .setIssuedAt(now)//签发token日志
                .signWith(key);//使用key签名
        if (millionSeconds > 0) {
            long expMillis = nowMillis + millionSeconds;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp); //设置过期时间
        }
        return builder.compact();
    }

    @Override
    public SecretKey generalKey() {
        byte[] keyBytes = Base64.decodeBase64(primaryKey);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }

    @Override
    public Claims parseJWT(String jwt) {
        SecretKey key = generalKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
    }
}
