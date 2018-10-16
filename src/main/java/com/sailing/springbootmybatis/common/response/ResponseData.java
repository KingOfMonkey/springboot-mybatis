package com.sailing.springbootmybatis.common.response;

import java.io.Serializable;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common
 * @Description: 封装web项目返回值
 * @date 2018/9/13 09:58
 */
public class ResponseData<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    /**
     * 私有构造函数，不允许外部调用，要求必须传入 返回码和提示信息
     */
    private ResponseData(){}


    public ResponseData(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 不带数据的构造函数（枚举）
     * @param responseEnum 状态以及信息枚举
     */
    public ResponseData(ResponseEnum responseEnum){
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    /**
     * 包含所有属性的构造函数（枚举）
     * @param responseEnum 状态以及信息枚举
     * @param data 数据体
     */
    public ResponseData(ResponseEnum responseEnum, T data){
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
