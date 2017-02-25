package com.ca.customer.dao.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Vivek on 20-01-2017.
 */
@Embeddable
public class AddressModel {

    public static final String TBL_ADDRESS = "TBL_ADDRESS";

    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;

    @Column(name = "POSTAL_ADDRESS_ID")
    private String postalAddress;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }


}
