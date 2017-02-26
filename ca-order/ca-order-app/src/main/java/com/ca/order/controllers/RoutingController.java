package com.ca.order.controllers;

import com.ca.order.api.DeliveryDetails;
import com.ca.order.api.RouteDetails;
import com.ca.order.exception.InvalidOrderRequest;
import com.ca.order.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vivek on 26-02-2017.
 */
@RestController
@RequestMapping("/delivery/{deliveryId}/routing/")
public class RoutingController {

    private DeliveryService deliveryService;

    @Autowired
    public RoutingController(DeliveryService deliveryService){
        this.deliveryService=deliveryService;
    }



    @RequestMapping(path = "update", method = RequestMethod.PUT)
    public RouteDetails updateRoute(@PathVariable Long deliveryId,@Validated @RequestBody RouteDetails routeDetails) throws IOException {
        if(deliveryId==null ){
            throw new InvalidOrderRequest("The order request is invalid");
        }
        return deliveryService.updateRoute(deliveryId, routeDetails);
    }

    @RequestMapping(path = "{routeId}", method = RequestMethod.GET)
    public RouteDetails getRoute(@PathVariable Long deliveryId,@PathVariable Long routeId) throws IOException {
        if(deliveryId==null){
            throw new InvalidOrderRequest("The order id is invalid");
        }
        return deliveryService.getRoute(deliveryId);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<RouteDetails> getAllRoutes(@PathVariable Long deliveryId) throws IOException {
        return deliveryService.getAllRoute(deliveryId);
    }
}
