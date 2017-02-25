package com.ca.order.controllers;

import com.ca.order.api.OrderApi;
import com.ca.order.exception.InvalidOrderRequest;
import com.ca.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/customer/{customerId}/order/")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public OrderApi create(@PathVariable Long customerId, @Validated @RequestBody OrderApi orderApi)  {
        return orderService.createOrder(customerId,orderApi);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.GET)
    public OrderApi getOrder(@PathVariable Long customerId,@PathVariable Long orderId) throws IOException {
        if(orderId==null){
            throw new InvalidOrderRequest("The order id is invalid");
        }
        return orderService.getOrder(orderId);
    }

    @RequestMapping(path = "orders", method = RequestMethod.GET)
    public List<OrderApi> getOrders(@PathVariable Long customerId) throws IOException {
        return orderService.getOrderList(customerId);
    }

    @RequestMapping(path = "update", method = RequestMethod.PUT)
    public OrderApi updateOrder(@PathVariable Long customerId,@Validated @RequestBody OrderApi orderApi) throws IOException {
        if(customerId==null ){
            throw new InvalidOrderRequest("The order request is invalid");
        }
        return orderService.updateOrder(customerId, orderApi);
    }

    @RequestMapping(path = "approve", method = RequestMethod.PUT)
    public OrderApi approveOrder(@PathVariable Long customerId,@Validated @RequestBody OrderApi orderApi) throws IOException {
        if(customerId==null ){
            throw new InvalidOrderRequest("The order request is invalid");
        }
        return orderService.approveOrder(customerId, orderApi);
    }

}
