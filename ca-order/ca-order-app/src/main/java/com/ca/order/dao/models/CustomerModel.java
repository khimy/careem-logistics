package com.ca.order.dao.models;

import com.ca.customer.dao.models.*;
import com.ca.customer.dao.models.AddressModel;
import com.ca.customer.dao.models.Status;

import javax.persistence.*;

/**
 * Created by Vivek on 19-01-2017.
 */
@Entity
@Table(name = CustomerModel.TBL_CUSTOMER)
public class CustomerModel extends AbstractPersistable {
    public static final String TBL_CUSTOMER= "TBL_CUSTOMER";

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "EMAIL_ID")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_ID", nullable = false)
    private EntityModel entityModel;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private com.ca.customer.dao.models.Status status;

    @Enumerated(EnumType.STRING)
    @Column(name="RATING")
    private Rating rating;


    @Embedded
    private com.ca.customer.dao.models.AddressModel address;

    public EntityModel getEntityModel() {
        return entityModel;
    }

    public void setEntityModel(EntityModel entityModel) {
        this.entityModel = entityModel;
    }

    public com.ca.customer.dao.models.Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public com.ca.customer.dao.models.AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
