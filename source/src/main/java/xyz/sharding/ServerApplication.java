package xyz.sharding;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import xyz.sharding.annotations.GeneralMapper;

/**
 * @author shisp
 * @date 2018-7-18 17:42:03
 * 
 *       import tk.mybatis.spring.annotation.MapperScan;
 *       使用MapperScan或MapperScannerConfigurer时需要使用tk.mybatis.spring.*，而不是org.mybatis.spring.*
 * 
 */
@SpringBootApplication
/*@EnableConfigurationProperties注解是用来开启对@ConfigurationProperties注解配置Bean的支持。
也就是@EnableConfigurationProperties注解告诉Spring Boot 使能支持@ConfigurationProperties*/
@EnableConfigurationProperties
@MapperScan(basePackages = "xyz.sharding.**.mapper", annotationClass = GeneralMapper.class)
public class ServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
