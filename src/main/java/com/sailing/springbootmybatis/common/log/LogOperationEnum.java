package com.sailing.springbootmybatis.common.log;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common.log
 * @Description: 操作类型枚举类
 * @date 2018/10/16 10：58
 */
public enum LogOperationEnum {
    SELECT,
    INSERT,
    UPDATE,
    DELETE;

    private LogOperationEnum(){
    }
}
