package com.ca.order.api;

import java.util.Date;

/**
 * Created by Vivek on 26-02-2017.
 */
public class RouteDetails {

    public Long id;
    public RoutingStatus routingStatus;
    public Date startDate;
    public Date estimatedEndDate;
    public Long estimatedDistance;
    public boolean isParticipated;
    public Long vendorId;
    public double offeredAmount; //amount offered to vendor
    public Long eta; //estimated time for participation
    public RoutingMode modeOfCommunication;
    public Name name = new Name(); //Name and mobile are for the person who is responsible for transit in that route
    public String mobile;


}
