package com.jmsw.framework.config.controller;

import com.jmsw.framework.config.entity.Properties;
import com.jmsw.framework.config.entity.PropertiesFile;
import com.jmsw.framework.config.respository.PropertiesFileRepository;
import com.jmsw.framework.config.respository.PropertiesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

@Api(tags = "配置管理")
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private PropertiesFileRepository propertiesFileRepository;

    @ApiOperation("查询配置信息")
    @RequestMapping("/index")
    public ModelAndView index(PropertiesFile propertiesFile) {
        List<Long> propertiesFileIds = propertiesRepository.findPropertiesFileId(
                propertiesFile.getApplication() != null ? propertiesFile.getApplication() : "",
                propertiesFile.getProfile() != null ? propertiesFile.getProfile() : "",
                propertiesFile.getLabel() != null ? propertiesFile.getLabel() : "");

        List<PropertiesFile> propertiesFileList = propertiesFileRepository.findAllById(propertiesFileIds);
        propertiesFileList.sort(new Comparator<PropertiesFile>() {
            @Override
            public int compare(PropertiesFile o1, PropertiesFile o2) {
                return o1.getApplication().compareTo(o2.getApplication()) * 100
                        + o1.getProfile().compareTo(o2.getProfile()) * 10
                        + o1.getLabel().compareTo(o2.getLabel());
            }
        });
        return new ModelAndView("index")
                .addObject("propertiesFileList", propertiesFileList);
    }

    @ApiOperation("编辑配置信息")
    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        PropertiesFile propertiesFile;
        if (null != id) {
            propertiesFile = propertiesFileRepository.findById(id).get();
        } else {
            propertiesFile = new PropertiesFile();
        }
        modelAndView.addObject("propertiesFile", propertiesFile);
        return modelAndView;
    }

    @ApiOperation("编辑配置信息")
    @RequestMapping("/edit.do")
    @Transactional
    public ModelAndView doEdit(PropertiesFile propertiesFile) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Date now = new Date();
        propertiesFile.setCreateDate(now);
        propertiesFile.setCreateUserId(user.getUsername());
        propertiesFile.setLastUpdateDate(now);
        propertiesFile.setLastUpdateUserId(user.getUsername());

        PropertiesFile propertiesFileDb = propertiesFileRepository.save(propertiesFile);

        this.save(propertiesFileDb.getId());
        return new ModelAndView("redirect:index");
    }

    @ApiOperation("删除配置")
    @RequestMapping("/delete.do")
    @Transactional
    public ModelAndView doDelete(Long id) {
        propertiesRepository.deleteByPropertiesFileId(id);
        return new ModelAndView("redirect:index");
    }

    @Transactional
    public List<Properties> save(Long id) throws IOException {
        PropertiesFile propertiesFile = propertiesFileRepository.findById(id).get();

        // 按PropertiesFileId删除原始数据
        propertiesRepository.deleteByPropertiesFileId(id);
        // 按Application Profile Label删除 处理修改上述三个参数的情况
        propertiesRepository.deleteByApplicationAndProfileAndLabel(propertiesFile.getApplication(), propertiesFile.getProfile(), propertiesFile.getLabel());

        java.util.Properties properties = new java.util.Properties();
        properties.load(new StringReader(propertiesFile.getContent()));

        List<Properties> propertiesList = new ArrayList<>();
        for (Map.Entry<Object, Object> propertiesEntry : properties.entrySet()) {
            Properties propertiesToSave = new Properties();

            propertiesToSave.setPropertiesFileId(propertiesFile.getId());
            propertiesToSave.setApplication(propertiesFile.getApplication());
            propertiesToSave.setProfile(propertiesFile.getProfile());
            propertiesToSave.setLabel(propertiesFile.getLabel());
            propertiesToSave.setKey(propertiesEntry.getKey().toString());
            propertiesToSave.setValue(propertiesEntry.getValue().toString());
            Date now = new Date();
            propertiesToSave.setCreateDate(now);
            propertiesToSave.setCreateUserId("");
            propertiesToSave.setLastUpdateDate(now);
            propertiesToSave.setLastUpdateUserId("");

            propertiesList.add(propertiesToSave);
        }

        return propertiesRepository.saveAll(propertiesList);
    }
}
