package com.ca.order.api;

import java.util.Date;
import java.util.List;

/**
 * Created by Vivek on 26-02-2017.
 */
public class DeliveryDetails {

    public  Long id;
    public Date timeOfArrival;
    public Long orderId;
    public Long calculatedDistance;
    public Double offeredSum;
    public DeliveryStatus deliveryStatus;
    public List<RouteDetails> routeDetails;
    public String comments;




}
