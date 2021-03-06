package com.sailing.springbootmybatis.mapper.one;

import com.sailing.springbootmybatis.bean.Userinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Map selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    //以上是自动生成，下面接口是自己添加
    List<Userinfo> selectAllUsers();
}