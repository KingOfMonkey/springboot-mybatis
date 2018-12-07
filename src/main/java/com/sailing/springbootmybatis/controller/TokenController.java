package com.sailing.springbootmybatis.controller;

import com.sailing.springbootmybatis.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.controller
 * @Description: jwt token 测试
 * @date 2018/12/5 15：28
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * 生成jwt
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    @RequestMapping(value = "/token/getToken", method = RequestMethod.GET)
    public String createToken(String id, String subject, long ttlMillis){
        return tokenService.createJWT(id,subject,ttlMillis);
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/token/verification", method = RequestMethod.POST)
    public Claims parseJWT(String jwt) throws Exception{
        return tokenService.parseJWT(jwt);
    }
}
