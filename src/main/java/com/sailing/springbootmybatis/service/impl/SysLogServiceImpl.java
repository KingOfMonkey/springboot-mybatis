package com.sailing.springbootmybatis.service.impl;

import com.sailing.springbootmybatis.bean.SysLog;
import com.sailing.springbootmybatis.mapper.SysLogMapper;
import com.sailing.springbootmybatis.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.service.impl
 * @Description: 日志操作业务实现类
 * @date 2018/10/16 14：34
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService{

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int insertLog(SysLog log) {
        return sysLogMapper.insert(log);
    }
}
