package com.sailing.springbootmybatis.config.wrapper;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @author baibing
 * @project: iemp-yjbj
 * @package: com.sailing.yjbj.config.wrapper
 * @Description: 自定义wrapper处理spring boot + mybatis返回结果为map时的key值转换为驼峰
 * @date 2018/11/1 11:34
 */
public class CustomWrapper extends MapWrapper {

    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if(useCamelCaseMapping){
            //此处调用google的guava库，因为项目集成了swagger，swagger中依赖于guava所以不用重复引进
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,name);
        }
        return name;
    }
}
