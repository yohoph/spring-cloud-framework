package com.jmsw.framework.config.respository;

import com.jmsw.framework.config.entity.PropertiesFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesFileRepository extends JpaRepository<PropertiesFile, Long> {
}
