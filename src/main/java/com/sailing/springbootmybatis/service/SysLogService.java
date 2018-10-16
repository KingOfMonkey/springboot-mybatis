package com.sailing.springbootmybatis.service;

import com.sailing.springbootmybatis.bean.SysLog;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service
 * @Description: 日志业务接口
 * @date 2018/10/16 14：33
 */
public interface SysLogService {

    int insertLog(SysLog log);
}
