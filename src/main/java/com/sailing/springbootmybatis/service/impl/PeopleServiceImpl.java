package com.sailing.springbootmybatis.service.impl;

import com.sailing.springbootmybatis.bean.People;
import com.sailing.springbootmybatis.common.response.BuildResponseUtil;
import com.sailing.springbootmybatis.common.response.ResponseData;
import com.sailing.springbootmybatis.mapper.two.PeopleMapper;
import com.sailing.springbootmybatis.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lenovo
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service.impl
 * @Description:
 * @date 2018/10/191137
 */
@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public ResponseData findAllPeople() {
        List<People> list = peopleMapper.selectAllPeople();
        return BuildResponseUtil.buildSuccessResponse(list);
    }
}
