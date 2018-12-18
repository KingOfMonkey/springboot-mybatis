package com.sailing.springbootmybatis.common.exception;

import com.sailing.springbootmybatis.common.response.BuildResponseUtil;
import com.sailing.springbootmybatis.common.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common
 * @Description: 全局异常统一处理类
 * @date 2018/9/14 10:32
 */
//@ControllerAdvice
@RestControllerAdvice(basePackages = {"com.sailing.springbootmybatis.controller"})
//类似于 controller 和 restController的区别，@RestController中的所有接口返回值都为 responseBody json 格式
public class ModuleControllerAdvice {

    private Logger SERVICE_LOGGER = LoggerFactory.getLogger(ServiceException.class);

    private Logger SERVER_LOGGER = LoggerFactory.getLogger(ModuleControllerAdvice.class);


    /**
     *  Logger类下有多个不同的error方法，根据传入参数的个数及类型的不同，自动选择不同的重载方法。
     * 当error(Object obj)只传入一个参数时会将异常对象作为Object使用，并最终当做String打印出来，
     * 当使用两个参数error(String message, Throwable t)，且第二个参数为Throwable时，才会将完整的异常堆栈打印出来。
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    private ResponseData handleServiceException(Exception e){
        String message;
        ResponseData responseData;
        if(e instanceof ServiceException){
            message = "业务受理失败, 原因:" + e.getLocalizedMessage();
            SERVICE_LOGGER.error("业务受理失败, 原因:{}", e.getLocalizedMessage(), e);
            responseData = BuildResponseUtil.buildServiceFailResponse(message);
        }else{
            message = "程序异常, 信息:" + e.getLocalizedMessage();
            SERVER_LOGGER.error("程序异常, 详细信息:{}", e.getLocalizedMessage() , e);
            responseData = BuildResponseUtil.buildServerErrorResponse(message);
        }
        return responseData;
    }
}
