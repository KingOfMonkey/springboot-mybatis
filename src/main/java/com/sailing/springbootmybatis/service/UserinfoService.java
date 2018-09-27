package com.sailing.springbootmybatis.service;

import com.sailing.springbootmybatis.bean.Userinfo;
import com.sailing.springbootmybatis.common.response.ResponseData;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service
 * @Description: service 业务接口
 * @date 2018/9/12 09:52
 */
public interface UserinfoService {

    ResponseData findById(Integer id);

    ResponseData findAllUsers();

    ResponseData findAllUsers(Integer page, Integer rows);

    ResponseData saveUser(Userinfo userinfo);

    ResponseData deleteUser(Integer id);

    ResponseData updateUser(Userinfo userinfo);
}
