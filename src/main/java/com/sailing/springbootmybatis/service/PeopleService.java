package com.sailing.springbootmybatis.service;

import com.sailing.springbootmybatis.common.response.ResponseData;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service
 * @Description: people业务接口
 * @date 2018/10/19 11：36
 */
public interface PeopleService {

    ResponseData findAllPeople();
}
