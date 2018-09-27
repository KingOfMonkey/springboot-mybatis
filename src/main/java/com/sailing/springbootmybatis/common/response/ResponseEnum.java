package com.sailing.springbootmybatis.common.response;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common
 * @Description: 定义返回状态枚举
 * @date 2018/9/13 10:41
 */
public enum ResponseEnum {

    SUCCESS(1, "请求成功"),//接口正常返回
    FAIL(0, "请求失败"),//请求失败，一般是参数校验没有通过
    SERVICE_EXCEPTION(21000, "业务异常"),//业务层异常
    SERVER_ERROR(51000, "程序异常");//程序异常，一般是bug，需要排查解决

    private Integer code;

    private String message;

    ResponseEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
