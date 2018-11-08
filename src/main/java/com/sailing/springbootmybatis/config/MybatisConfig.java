package com.sailing.springbootmybatis.config;

import com.sailing.springbootmybatis.config.wrapper.MapWrapperFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author baibing
 * @project: iemp-yjbj
 * @package: com.sailing.yjbj.config
 * @Description: mybatis配置类,将自定义的MapWrapperFactory覆盖默认的ObjectWrapperFactory
 * @date 2018/11/1 11：42
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer(){
        System.out.println("initiazing ConfigurationCustomizer....");
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setObjectWrapperFactory(new MapWrapperFactory());
            }
        };
    }
}
