package com.jmsw.framework.config.respository;

import com.jmsw.framework.config.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long>, JpaSpecificationExecutor {
    Long deleteByPropertiesFileId(Long propertiesFileId);

    Long deleteByApplicationAndProfileAndLabel(String application, String profile, String label);

    @Query("select distinct(p.propertiesFileId) from Properties p where p.application like %?1% and profile like %?2% and label like %?3%")
    List<Long> findPropertiesFileId(String application, String profile, String label);
}
