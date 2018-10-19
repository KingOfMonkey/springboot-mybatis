package com.sailing.springbootmybatis.controller;

import com.sailing.springbootmybatis.common.response.ResponseData;
import com.sailing.springbootmybatis.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.controller
 * @Description: people controller
 * @date 2018/10/19 16:59
 */
@RestController
@Api(value = "PEOPLE", description = "PEOPLE信息测试controller")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    /**
     * 查询所有people接口
     * @return
     */
    @RequestMapping(value = "/peoples", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有people接口（测试多数据源）", notes = "查询所有people接口（测试多数据源）")
    public ResponseData findAllPeople(){
        return peopleService.findAllPeople();
    }

    /**
     * 删除指定id的people
     * @param id
     * @return
     */
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除指定id的people接口（测试回滚）", notes = "删除指定id的people接口（测试回滚）")
    public ResponseData deletePeoPle(@PathVariable Integer id){
        return peopleService.deleteByPrimaryId(id);
    }
}
