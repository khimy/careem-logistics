package com.ca.order.controllers;

import com.ca.order.services.DeliveryService;

/**
 * Created by Vivek on 26-02-2017.
 */
public class DeliveryController {

    private DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService=deliveryService;
    }




}
