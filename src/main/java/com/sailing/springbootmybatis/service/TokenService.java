package com.sailing.springbootmybatis.service;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service
 * @Description: jwt service 接口
 * @date 2018/12/6 09：23
 */
public interface TokenService {

    /**
     * 生成token接口
     * @param id token的id
     * @param sub token的持有者
     * @param millionSeconds 如果>0 则代表token过期时间
     * @return 生成的token令牌
     */
    String createJWT(String id, String sub, long millionSeconds);

    /**
     * 根据密钥生成签名所需要的key
     * @return key
     */
    SecretKey generalKey();

    /**
     * 解密token
     * @param jwt token值
     * @return
     */
    Claims parseJWT(String jwt);
}
