/**
 * Copyright (c) 2018-2019 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * 
 * Created on 2018年1月18日
 * Id: CommonAutoConfig.java, 2018年1月18日 下午4:58:13 yehao
 */
package com.jmsw.common.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yehao
 * @version 2.0
 * @date 2018年1月18日 下午4:58:13
 * @Description 通用的配置项
 */
@Configuration
@ComponentScan(basePackages = "com.jmsw.common")
public class CommonAutoConfig {

}
