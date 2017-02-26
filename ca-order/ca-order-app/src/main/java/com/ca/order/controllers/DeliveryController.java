package com.ca.order.controllers;

import com.ca.order.api.DeliveryDetails;
import com.ca.order.api.NotificationApi;
import com.ca.order.api.OrderApi;
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
@RequestMapping("/order/{orderId}/delivery/")
public class DeliveryController {

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService=deliveryService;
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public DeliveryDetails create(@PathVariable Long orderId, @Validated @RequestBody DeliveryDetails deliveryDetails)  {
        return deliveryService.createDelivery(orderId, deliveryDetails);
    }

        @RequestMapping(path = "update", method = RequestMethod.PUT)
        public DeliveryDetails updateDelivery(@PathVariable Long orderId,@Validated @RequestBody DeliveryDetails deliveryDetails) throws IOException {
            if(orderId==null ){
                throw new InvalidOrderRequest("The order request is invalid");
            }
            return deliveryService.updateDelivery(orderId, deliveryDetails);
        }

        @RequestMapping(path = "{deliveryId}", method = RequestMethod.GET)
        public DeliveryDetails getDelivery(@PathVariable Long orderId,@PathVariable Long deliveryId) throws IOException {
            if(orderId==null){
                throw new InvalidOrderRequest("The order id is invalid");
            }
            return deliveryService.getDelivery(deliveryId);
        }

        @RequestMapping(path = "", method = RequestMethod.GET)
        public List<DeliveryDetails> getAllDelivery(@PathVariable Long orderId) throws IOException {
            return deliveryService.getAllDelivery(orderId);
        }


    @RequestMapping(path = "notify", method = RequestMethod.GET)
    public NotificationApi getNotification(@PathVariable Long orderId) throws IOException {
        if(orderId==null ){
            throw new InvalidOrderRequest("The order request is invalid");
        }
        NotificationApi orderApi2= deliveryService.getNotification(orderId);
        return orderApi2;
    }

}
