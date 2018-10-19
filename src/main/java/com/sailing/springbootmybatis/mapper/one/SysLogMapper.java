package com.sailing.springbootmybatis.mapper.one;

import com.sailing.springbootmybatis.bean.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.mapper
 * @Description: 日志mapper文件
 * @date 2018/10/16 14：17
 */
@Mapper
public interface SysLogMapper {

    int insert(SysLog log);
}
