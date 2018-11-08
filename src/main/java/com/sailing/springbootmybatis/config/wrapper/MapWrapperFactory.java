package com.sailing.springbootmybatis.config.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * @author baibing
 * @project: iemp-yjbj
 * @package: com.sailing.yjbj.config.wrapper
 * @Description: 实现接口 ObjectWrapperFactory,通过包装工厂来创建自定义的wrapper
 * @date 2018/11/1 11：38
 */
public class MapWrapperFactory implements ObjectWrapperFactory{

    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new CustomWrapper(metaObject,(Map)object);
    }
}
