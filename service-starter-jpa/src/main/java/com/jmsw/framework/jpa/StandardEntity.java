package com.jmsw.framework.jpa;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@EntityListeners(value = EntityListener.class)
@MappedSuperclass
public class StandardEntity implements Versioned,SoftDelete,Updatable,Creatable{
    private static final long serialVersionUID = 5642226839555253331L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    private String id;

    @Version
    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    @Column(name = "UPDATE_TS")
    protected Date updateTs;

    @Column(name = "UPDATED_BY", length = 50)
    protected String updatedBy;

    @Column(name = "DELETE_TS")
    protected Date deleteTs;

    @Column(name = "DELETED_BY", length = 50)
    protected String deletedBy;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }
    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }
    @Override
    public Date getCreateTs() {
        return createTs;
    }
    @Override
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    @Override
    public Date getUpdateTs() {
        return updateTs;
    }
    @Override
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }
    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }
    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    @Override
    public Boolean isDeleted() {
        return deleteTs != null;
    }
    @Override
    public Date getDeleteTs() {
        return deleteTs;
    }
    @Override
    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }
    @Override
    public String getDeletedBy() {
        return deletedBy;
    }
    @Override
    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
