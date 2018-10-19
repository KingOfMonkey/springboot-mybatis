package com.sailing.springbootmybatis.controller;

import com.sailing.springbootmybatis.bean.Userinfo;
import com.sailing.springbootmybatis.common.log.LogOperationEnum;
import com.sailing.springbootmybatis.common.log.annotation.MyLog;
import com.sailing.springbootmybatis.common.response.BuildResponseUtil;
import com.sailing.springbootmybatis.common.response.ResponseData;
import com.sailing.springbootmybatis.common.websocket.WebSocketServer;
import com.sailing.springbootmybatis.service.RedisService;
import com.sailing.springbootmybatis.service.UserinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.controller
 * @Description: Userinfo controller 控制层
 * @date 2018/9/12 10:07
 */
@RestController
@Api(value = "USERINFO", description = "用户信息测试controller")
public class UserinfoController{

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private RedisService redisService;



    /**
     * 查找指定id对应的用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @MyLog(type = LogOperationEnum.SELECT,value = "查询指定id的用户信息")
    @ApiOperation(value = "查询指定id的用户信息接口", notes = "查询指定id的用户信息接口")
    public ResponseData getUser(@PathVariable(value = "id") Integer id){
        return userinfoService.findById(id);
    }

    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @MyLog(type = LogOperationEnum.SELECT,value = "查询全部用户信息")
    @ApiOperation(value = "查询所有用户信息接口", notes = "查询所有用户信息接口")
    public ResponseData getAllUsers(){
        return userinfoService.findAllUsers();
    }

    /**
     * 查找所有用户（带分页）
     * @param page 当前页数
     * @param rows 每页显示条数
     * @return
     */
    @RequestMapping(value = "/users/p", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户信息接口(带分页)", notes = "查询所有用户信息接口(带分页)")
    public ResponseData getAllUsers(Integer page, Integer rows){
        return userinfoService.findAllUsers(page, rows);
    }

    /**
     * 新增用户 (带参数校验@Valid)
     * 注意：BindingResult 对象必须在 @Valid 的紧挨着的后面，否则接收不到错误信息
     * @Valid 可以校验json 也可以校验表单传递的对象属性
     * @param userinfo
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @MyLog(type = LogOperationEnum.INSERT, value = "新增用户信息")
    @ApiOperation(value = "新增用户接口(包含参数校验)", notes = "新增用户接口(包含参数校验)")
    public ResponseData saveUserinfo(@RequestBody @Valid Userinfo userinfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return BuildResponseUtil.buildFailResponse(bindingResult.getFieldError().getDefaultMessage());
        }
        return userinfoService.saveUser(userinfo);
    }

    /**
     * 删除指定用户
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除指定id的用户信息接口", notes = "删除指定id的用户信息接口")
    public ResponseData deleteUser(@PathVariable Integer id){
        return userinfoService.deleteUser(id);
    }

    /**
     * 更新用户
     * @param userinfo
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ApiOperation(value = "更新指定id用户信息接口", notes = "更新指定id用户信息接口")
    public ResponseData updateUserinfo(@RequestBody Userinfo userinfo){
        return userinfoService.updateUser(userinfo);
    }

    /**
     * 给指定用户推送消息
     * @param userName 用户名
     * @param message 消息
     * @throws IOException
     */
    @RequestMapping(value = "/socket", method = RequestMethod.GET)
    @ApiIgnore //使用此注解忽略方法的暴露，也可以用在controller上
    @ApiOperation(value = "给指定用户推送socket消息接口", notes = "给指定用户推送socket消息接口")
    public void testSocket(@RequestParam String userName,@RequestParam String message){
        webSocketServer.sendInfo(userName, message);
    }

    /**
     * 测试redis接口保存String类型action
     * @param address
     * @return
     */
    @RequestMapping(value = "/redis", method = RequestMethod.POST)
    @ApiIgnore //使用此注解忽略方法的暴露，也可以用在controller上
    @ApiOperation(value = "redis中添加String数据接口", notes = "redis中添加String数据接口")
    public ResponseData setString(@RequestBody String address){
        System.out.println(address);
        return redisService.setValue(address);
    }

    /**
     * 测试redis接口保存实体类型action
     * @param userinfo
     * @return
     */
    @RequestMapping(value = "/redis/userinfo", method = RequestMethod.POST)
    @ApiIgnore //使用此注解忽略方法的暴露，也可以用在controller上
    @ApiOperation(value = "redis中添加Userinfo实体接口", notes = "redis中添加Userinfo实体接口")
    public ResponseData setEntity(@RequestBody Userinfo userinfo){
        return redisService.setEntityValue(userinfo);
    }
    /**
     * 测试redis接口读取实体类型action
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/userinfo/{key}", method = RequestMethod.GET)
    @ApiIgnore //使用此注解忽略方法的暴露，也可以用在controller上
    @ApiOperation(value = "redis中读取指定key对应的数据接口", notes = "redis中读取指定key对应的数据接口")
    public ResponseData getEntity(@PathVariable String key){
        return redisService.getEntityValue(key);
    }

    /**
     *
     * @param list
     * @return
     */
    @RequestMapping(value = "/redis/userList", method = RequestMethod.POST)
    @ApiIgnore //使用此注解忽略方法的暴露，也可以用在controller上
    @ApiOperation(value = "redis中添加包含Userinfo实体的集合接口", notes = "redis中添加包含Userinfo实体的集合接口")
    public ResponseData setCollection(@RequestBody List<Userinfo> list){
        return redisService.setCollectionValue(list);
    }

    /**
     * 测试redis接口读取实体类型action
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/userList/{key}", method = RequestMethod.GET)
    @ApiOperation(value = "redis中读取指定key对应的集合数据接口", notes = "redis中读取指定key对应的集合数据接口")
    public ResponseData getCollection(@PathVariable String key){
        return redisService.getCollectionValue(key);
    }

}
