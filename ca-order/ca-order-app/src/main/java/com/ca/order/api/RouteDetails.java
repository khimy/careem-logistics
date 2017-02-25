package com.ca.order.api;

import java.util.Date;

/**
 * Created by Vivek on 26-02-2017.
 */
public class RouteDetails {

     Long id;
     RoutingStatus routingStatus;
     Date startDate;
     Date estimatedEndDate;
     boolean isParticipated;
     long vendorId;
     double offeredAmount; //amount offered to vendor
     Long eta; //estimated time for participation
     public RoutingMode modeOfCommunication;
     public Name name=new Name(); //Name and mobile are for the person who is responsible for transit in that route
     public String mobile;


}
