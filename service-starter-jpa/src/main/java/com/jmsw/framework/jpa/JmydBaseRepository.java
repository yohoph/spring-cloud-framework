package com.jmsw.framework.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yehao
 * @version 1.0
 * @date 2020/9/27 14:58
 * @Description 获取基础的思想
 */
@NoRepositoryBean
public interface JmydBaseRepository<T,PK> extends JpaRepository<T,PK> {

    static final String SOFT_DELETE_CLAUSE = "delete_ts is null";

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} t SET t.deleteTs = ?2 WHERE t.id = ?1")
    void softDelete(String id, Date deleteDate);

    default void softDelete(String id) {
        softDelete(id, new Date());
    }

}
