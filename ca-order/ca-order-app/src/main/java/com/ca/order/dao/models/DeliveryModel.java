package com.ca.order.dao.models;

import com.ca.order.api.DeliveryStatus;
import com.ca.order.api.RouteDetails;
import com.ca.order.controllers.DeliveryController;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Vivek on 26-02-2017.
 */
@Entity
@Table(name = DeliveryModel.TBL_DELIVERY_DETAIL)
public class DeliveryModel extends AbstractPersistable{
    public static final String TBL_DELIVERY_DETAIL= "TBL_DELIVERY_DETAIL";

    @Temporal(TemporalType.DATE)
    @Column(name="TIME_OF_ARRIVAL")
    private Date timeOfArrival;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="ORDER_ID")
    public OrderModel orderModel;

    @Column(name="CALCULATED_DISTANCE")
    public Long calculatedDistance;

    @Column(name="OFFERED_AMOUNT")
    public Double offeredAmount;

    @Enumerated(EnumType.STRING)
    @Column(name="DELIVERY_STATUS")
    public DeliveryStatus deliveryStatus;

    @OneToMany
    public List<RouteDetails> routeDetails;

    @Column(name="COMMENTS")
    public String comments;

    public Date getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Date timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public Long getCalculatedDistance() {
        return calculatedDistance;
    }

    public void setCalculatedDistance(Long calculatedDistance) {
        this.calculatedDistance = calculatedDistance;
    }

    public Double getOfferedAmount() {
        return offeredAmount;
    }

    public void setOfferedAmount(Double offeredAmount) {
        this.offeredAmount = offeredAmount;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<RouteDetails> getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(List<RouteDetails> routeDetails) {
        this.routeDetails = routeDetails;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
