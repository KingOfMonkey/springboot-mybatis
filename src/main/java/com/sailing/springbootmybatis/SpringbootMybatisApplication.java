package com.sailing.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis
 * @Description: 主项目的启动类    注意：1.官方建议将此类放到主目录下，以便能扫描到 mapper、service 和 controller
 *                                     2.如果没有按照上面配置，启动项目会报注解找不到相关class错误，解决方法:是在
 *                                       启动类上面增加 @ComponentScan（扫描所有controller和 service 以及 Component）
 *                                       和 @MapperScan(扫描所有mapper) 两个注解。
 * @date 2018/9/12 10：07
 */
//@ComponentScan(value = {"com.sailing.springbootmybatis.*"})
//@MapperScan(basePackages = {"com.sailing.springbootmybatis.mapper"})// 加了这行代码可以不用给mapper添加 @mapper注解
@SpringBootApplication
public class SpringbootMybatisApplication implements CommandLineRunner{

	@Autowired
	@Qualifier("datasourceOne")
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println(dataSource);
	}
}
