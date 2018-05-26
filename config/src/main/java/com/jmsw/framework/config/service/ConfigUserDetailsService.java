package com.jmsw.framework.config.service;

import com.jmsw.framework.config.entity.Properties;
import com.jmsw.framework.config.respository.PropertiesRepository;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConfigUserDetailsService implements UserDetailsService {
    private BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

    {
        basicTextEncryptor.setPassword("vj29t9BkAJMCcMxX4J8QyH7Go1lRwchw");
    }

    @Value("${user.admin.password}")
    private String adminPassword;

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password;
        if ("admin".equals(username)) {
            password = adminPassword;
        } else {
            Properties properties = new Properties();
            properties.setApplication("config");
            properties.setProfile("mo");
            properties.setLabel("master");
            properties.setKey("user." + username + ".password");

            Properties propertiesDb = propertiesRepository.findOne(Example.of(properties)).get();
            if (null == propertiesDb) {
                throw new UsernameNotFoundException(username + "未配置");
            }

            password = propertiesDb.getValue();
        }
        User user = new User(username, basicTextEncryptor.decrypt(password), Collections.emptyList());
        return user;
    }
}
