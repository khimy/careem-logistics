package com.ca.order.dao.models;

import com.ca.order.api.Name;
import com.ca.order.api.RoutingMode;
import com.ca.order.api.RoutingStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vivek on 26-02-2017.
 */
@Entity
@Table(name = RoutingModel.TBL_ROUTING_DETAIL)
public class RoutingModel extends AbstractPersistable {
    public static final String TBL_ROUTING_DETAIL= "TBL_ROUTING_DETAIL";

    @Enumerated(EnumType.STRING)
    @Column(name="ROUTING_STATUS")
    private RoutingStatus routingStatus;

    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name="ESTIMATED_DATE")
    private Date estimatedEndDate;

    @Column(name = "ESTIMATED_DISTANCE")
    private Long estimatedDistance;

    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE")
    private Date endDate;

    @Column(name="IS_PARTICIPATED")
    private boolean isParticipated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="VENDOR_ID")
    private VendorModel vendorModel;

    @Column(name="OFFERED_AMOUNT")
    private double offeredAmount; //amount offered to vendor

    @Column(name="ETA")
    private Long eta; //estimated time for participation in hours

    @Enumerated(EnumType.STRING)
    @Column(name="COMMUNICATION_MODE")
    private RoutingMode modeOfCommunication;

    @Column(name="NAME")
    private String name; //Name and mobile are for the person who is responsible for transit in that route

    @Column(name="MOBILE")
    private String mobile;

    public Long getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(Long estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public RoutingStatus getRoutingStatus() {
        return routingStatus;
    }

    public void setRoutingStatus(RoutingStatus routingStatus) {
        this.routingStatus = routingStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(Date estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isParticipated() {
        return isParticipated;
    }

    public void setParticipated(boolean isParticipated) {
        this.isParticipated = isParticipated;
    }

    public VendorModel getVendorModel() {
        return vendorModel;
    }

    public void setVendorModel(VendorModel vendorModel) {
        this.vendorModel = vendorModel;
    }

    public double getOfferedAmount() {
        return offeredAmount;
    }

    public void setOfferedAmount(double offeredAmount) {
        this.offeredAmount = offeredAmount;
    }

    public Long getEta() {
        return eta;
    }

    public void setEta(Long eta) {
        this.eta = eta;
    }

    public RoutingMode getModeOfCommunication() {
        return modeOfCommunication;
    }

    public void setModeOfCommunication(RoutingMode modeOfCommunication) {
        this.modeOfCommunication = modeOfCommunication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
