package com.sailing.springbootmybatis.mapper;

import com.sailing.springbootmybatis.bean.Userinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    //以上是自动生成，下面接口是自己添加
    List<Userinfo> selectAllUsers();
}