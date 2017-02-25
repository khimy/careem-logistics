package com.ca.order.dao.models;

import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Neeraj Mittal <mittal.neeraj@gmail.com>
 */
@MappedSuperclass
abstract class AbstractPersistable implements Persistable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name="CREATED_DATE")
    private Date createdDate;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Temporal(TemporalType.DATE)
    @Column(name="MODIFIED_DATE")
    private Date modifiedDate;

    @Version
    @Column(name = "OPT_LOCK", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;

    @Transient
    private long syncTime;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Persistable#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Must be {@link Transient} in order to ensure that no JPA provider complains because of a missing setter.
     *
     * @see DATAJPA-622
     * @see Persistable#isNew()
     */
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        }

        AbstractPersistable that = (AbstractPersistable) obj;

        return null != this.getId() && this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

    @PostPersist
    @PostUpdate
    @PostLoad
    private void resetSyncTime() {
        syncTime = System.currentTimeMillis();
        System.out.println("Notification.resetSyncTime called on Notification id: " + getId());
    }

    public long getCachedAge() {
        return System.currentTimeMillis() - syncTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
