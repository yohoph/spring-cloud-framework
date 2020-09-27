package com.jmsw.framework.jpa;

import com.jmsw.framework.core.context.JmswContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @author yehao
 * @version 1.0
 * @date 2020/9/27 10:35
 * @Description 实体监听器
 */
public class EntityListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PrePersist
    public void prePersist(Object source){
        logger.debug("prePersist..." + source);
        if(source instanceof Creatable){
            Creatable create = (Creatable) source;
            if(create.getCreateTs() == null){
                create.setCreateTs(new Date());
                String id = JmswContext.getRequestHeader().getUserId();
                if(StringUtils.isNotEmpty(id)){
                    create.setCreatedBy(id);
                }
            }
        }
        if(source instanceof Updatable) {
            Updatable update = (Updatable) source;
            if(update.getUpdateTs() == null){
                update.setUpdateTs(new Date());
                String id = JmswContext.getRequestHeader().getUserId();
                if(StringUtils.isNotEmpty(id)){
                    update.setUpdatedBy(id);
                }
            }
        }
    }

    @PreUpdate
    public void preupdate(Object source){
        logger.debug("preupdate..." + source);
        if(source instanceof Updatable) {
            Updatable update = (Updatable) source;
            update.setUpdateTs(new Date());
            String id = JmswContext.getRequestHeader().getUserId();
            if(StringUtils.isNotEmpty(id)){
                update.setUpdatedBy(id);
            }
        }
    }

//    @PreRemove
//    public void preRemove(Object source){
//        System.out.println("@PreRemove：" + source);
//    }
//
//    @PostPersist
//    public void postpersist(Object source){
//        System.out.println("@postpersist：" + source);
//    }
//
//    @PostUpdate
//    public void postupdate(Object source){
//        System.out.println("@postupdate：" + source);
//    }
//
//    @PostRemove
//    public void postRemove(Object source){
//        System.out.println("@postRemove：" + source);
//    }

}
