package com.sailing.springbootmybatis.service;

import com.sailing.springbootmybatis.bean.Userinfo;
import com.sailing.springbootmybatis.common.response.BuildResponseUtil;
import com.sailing.springbootmybatis.common.response.ResponseData;
import com.sailing.springbootmybatis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service
 * @Description: redis 测试业务层
 * @date 2018/9/18 15：09
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;//内部封装了 RedisTemplate<String, Object>


    /**
     * 测试存储 String 类型数据
     * @param address
     * @return
     */
    public ResponseData setValue(String address){
        stringRedisTemplate.opsForValue().set("address", address);
        return BuildResponseUtil.buildSuccessResponse();
    }

    /**
     * 测试存储 Userinfo 类型数据
     * @param userinfo
     * @return
     */
    public ResponseData setEntityValue(Userinfo userinfo){
        String key = "user:" + userinfo.getUserId();
        redisUtil.set(key, userinfo);
        return BuildResponseUtil.buildSuccessResponse();
    }

    /**
     * 测试读取 Userinfo 类型数据
     * @param key
     * @return
     */
    public ResponseData getEntityValue(String key){
        boolean flag = redisUtil.hasKey(key);
        Userinfo userinfo = null;
        System.out.println(flag);
        if(flag){
            userinfo = (Userinfo) redisUtil.get(key);
        }
        return BuildResponseUtil.buildSuccessResponse(userinfo);
    }

    /**
     * 测试存储 Collection 类型数据
     * @param list
     * @return
     */
    public ResponseData setCollectionValue(List list){
        String key = "list:01";
        redisUtil.set(key, list);
        return BuildResponseUtil.buildSuccessResponse();
    }

    /**
     * 测试读取 Collection 类型数据
     * @param key
     * @return
     */
    public ResponseData getCollectionValue(String key){
        boolean flag = redisUtil.hasKey(key);
        List list = null;
        if(flag){
            list = (List) redisUtil.get(key);
        }
        return BuildResponseUtil.buildSuccessResponse(list);
    }
}
