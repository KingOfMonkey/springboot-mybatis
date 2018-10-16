package com.sailing.springbootmybatis.common.log.annotation;

import java.lang.annotation.*;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.annotation
 * @Description: 自定义日志注解
 * @date 2018/10/12 14：30
 */
@Target(ElementType.METHOD)//注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {

    int type() default 0;

    String value() default "日志注解";
}
