package com.ca.order.dao.models;

import com.ca.customer.dao.models.CustomerModel;

import javax.persistence.*;

/**
 * Created by Vivek on 19-01-2017.
 */
@Entity
@Table(name = OrderModel.TBL_ORDER)
public class OrderModel extends AbstractPersistable {
    public static final String TBL_ORDER = "TBL_ORDER";

    @Column(name="CustomerId")
    private CustomerModel customerModel;

    @Column(name="IS_PICK_UP")
    private boolean isPickUp;

    @Column(name="QUOTED_PRICE")
    private double quotedPrice;

    @Column(name="OFFERED_PRICE")
    private double offeredPrice;

    @Column(name="QUANTITY")
    private double quantity;

    @Column(name="WEIGHT")
    private double weight;

    @Column(name="PRODUCT_DESC")
    private String productDescription;

    @Column(name="COMMENTS")
    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @OneToOne
    private AddressModel pickUpAddress;

    @OneToOne
    private AddressModel dropAddress;

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public boolean isPickUp() {
        return isPickUp;
    }

    public void setPickUp(boolean isPickUp) {
        this.isPickUp = isPickUp;
    }

    public double getQuotedPrice() {
        return quotedPrice;
    }

    public void setQuotedPrice(double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AddressModel getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(AddressModel pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public AddressModel getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(AddressModel dropAddress) {
        this.dropAddress = dropAddress;
    }
}
