package com.jmsw.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yehao
 * @version 1.0
 * @date 2018/5/23 19:56
 * @Description 配置服务器
 */
@SpringBootApplication
@EnableSwagger2
@EnableConfigServer
public class ConfigServerApplication extends SpringBootServletInitializer {

    public static final String CURRENT_VERSION = "v1";
    public static final String COMPATIBLE_VERSION = "v1,v2";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigServerApplication.class);
    }

    @Bean
    public Docket docket(@Value("${swagger.enable:true}")boolean enableSwagger) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("积木三维配置中心")
                                .description("当前API版本" + CURRENT_VERSION + " 兼容API版本" + COMPATIBLE_VERSION)
                                .version(CURRENT_VERSION)
                                .build())
                .select().apis(RequestHandlerSelectors.basePackage(ConfigServerApplication.class.getPackage().getName())).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }

}
