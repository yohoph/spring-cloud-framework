package com.jmsw.framework.core.autoconfig;

import com.jmsw.framework.core.feign.JmswRequestInterceptor;
import com.jmsw.framework.core.feign.JmswSpringMvcContract;
import feign.Contract;
import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;

@Configuration
@ConditionalOnClass(Feign.class)
public class FeignAutoConfig {
    @Bean
    public Contract jmswSpringMvcContract(ConversionService conversionService) {
        return new JmswSpringMvcContract(Collections.emptyList(), conversionService);
    }

    @Bean
    public JmswRequestInterceptor jmswRequestInterceptor(){
        return new JmswRequestInterceptor();
    }
}
