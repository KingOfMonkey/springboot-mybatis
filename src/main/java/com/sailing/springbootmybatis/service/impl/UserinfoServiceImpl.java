package com.sailing.springbootmybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sailing.springbootmybatis.bean.Userinfo;
import com.sailing.springbootmybatis.common.response.BuildResponseUtil;
import com.sailing.springbootmybatis.common.response.ResponseData;
import com.sailing.springbootmybatis.common.exception.ServiceException;
import com.sailing.springbootmybatis.mapper.one.UserinfoMapper;
import com.sailing.springbootmybatis.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service.impl
 * @Description: service实现类
 * @date 2018/9/12 10:03
 */
@Service
@Transactional(value = "transactionManagerOne")
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserinfoMapper userinfoMapper;

    /**
     * 查找指定id对应的用户
     * @param id
     * @return
     */
    @Override
    public ResponseData findById(Integer id) {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(id);
        if(userinfo == null){
            throw new ServiceException("未找到相关用户信息");
        }
        return BuildResponseUtil.buildSuccessResponse(userinfo);
    }

    /**
     * 查找所有用户
     * @return
     */
    @Override
    public ResponseData findAllUsers() {
        List<Userinfo>  list = userinfoMapper.selectAllUsers();
        return BuildResponseUtil.buildSuccessResponse(list);
    }

    /**
     * 查找所有用户（带分页）
     * @param page 当前页数
     * @param rows 每页显示条数
     * @return
     */
    @Override
    public ResponseData findAllUsers(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Userinfo>  list = userinfoMapper.selectAllUsers();
        PageInfo<Userinfo> pageInfo = new PageInfo<>(list);
        return BuildResponseUtil.buildSuccessResponse(pageInfo);
    }

    /**
     * 新增用户
     * @param userinfo
     * @return
     */
    @Override
    public ResponseData saveUser(Userinfo userinfo) {
        userinfoMapper.insert(userinfo);
        return BuildResponseUtil.buildSuccessResponse();
    }

    /**
     * 删除指定用户
     * @param id 用户id
     * @return
     */
    @Override
    public ResponseData deleteUser(Integer id) {
        userinfoMapper.deleteByPrimaryKey(id);
        System.out.println(10/0);
        return BuildResponseUtil.buildSuccessResponse();
    }

    /**
     * 更新用户信息
     * @param userinfo
     * @return
     */
    @Override
    public ResponseData updateUser(Userinfo userinfo) {
        userinfoMapper.updateByPrimaryKey(userinfo);
        return BuildResponseUtil.buildSuccessResponse();
    }

}
