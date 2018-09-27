package com.sailing.springbootmybatis.common.response;


/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common
 * @Description: web api 统一返回结果工具类
 * @date 2018/9/13 10:20
 */
public class BuildResponseUtil {

    /**
     * 操作成功，不带数据
     * @return 统一成功返回结果体
     */
    public static ResponseData buildSuccessResponse(){
        return new ResponseData(ResponseEnum.SUCCESS);
    }

    /**
     * 操作成功，带返回数据
     * @param data 统一成功返回结果体
     * @return
     */
    public static ResponseData buildSuccessResponse(Object data){
        return new ResponseData(ResponseEnum.SUCCESS, data);
    }

    /**
     * 请求失败（使用自定义提示信息）
     * @return 统一失败返回结果体
     */
    public static ResponseData buildFailResponse(String errmsg){
        return new ResponseData(ResponseEnum.FAIL.getCode(), errmsg);
    }

    /**
     * 业务受理失败（使用自定义异常信息）
     * @param errmsg
     * @return
     */
    public static ResponseData buildServiceFailResponse(String errmsg){
        return new ResponseData(ResponseEnum.SERVICE_EXCEPTION.getCode(), errmsg);
    }

    /**
     * 程序异常 (使用自定义异常信息）
     * @param errmsg
     * @return
     */
    public static ResponseData buildServerErrorResponse(String errmsg){
        return new ResponseData(ResponseEnum.SERVER_ERROR.getCode(), errmsg);
    }
}
