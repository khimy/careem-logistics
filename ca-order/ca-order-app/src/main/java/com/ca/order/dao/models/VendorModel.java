package com.ca.order.dao.models;

import javax.persistence.*;

/**
 * Created by Vivek on 26-02-2017.
 */
@Entity
@Table(name = VendorModel.TBL_VENDOR)
public class VendorModel extends AbstractPersistable{
    public static final String TBL_VENDOR= "TBL_VENDOR";

    @Column(name="NAME")
    private String name;

    @Column(name="MOBILE")
    private String cellPhone;

    @Column(name="EMAIL")
    private String email;

    @OneToOne
    @JoinColumn(name="ADDRESS_ID")
    private AddressModel addressModel;

    @Column(name="IS_ACTIVE")
    private boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
