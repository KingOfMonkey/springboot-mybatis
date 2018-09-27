package com.sailing.springbootmybatis.common.exception;


/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common
 * @Description: 自定义业务异常类
 * @date 2018/9/14 10:21
 */
public class ServiceException extends RuntimeException{

    /**
     * 不提供无参构造函数，必须指定异常的详细信息
     * @param errmsg 错误信息
     */
    public ServiceException(String errmsg){
        super(errmsg);
    }
}
