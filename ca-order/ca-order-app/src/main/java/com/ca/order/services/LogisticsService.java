package com.ca.order.services;

import com.ca.order.api.DeliveryStatus;
import com.ca.order.api.OrderApi;
import com.ca.order.dao.models.DeliveryModel;
import com.ca.order.dao.repositories.DeliveryRepository;
import com.ca.order.dao.repositories.OrderRepository;
import com.ca.order.dao.repositories.RoutingRepository;
import com.ca.order.processor.DeliveryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Vivek on 19-01-2017.
 */
@Service
public class LogisticsService {

    private OrderRepository orderRepository;
    private DeliveryRepository deliveryRepository;
    private RoutingRepository routingRepository;

    @Autowired
    public LogisticsService(OrderRepository orderRepository,DeliveryRepository deliveryRepository,RoutingRepository routingRepository){
        this.orderRepository = orderRepository;
        this.deliveryRepository=deliveryRepository;
        this.routingRepository=routingRepository;
    }

    @Transactional
    public OrderApi createDelivery(OrderApi orderApi) {
        DeliveryModel deliveryModel=new DeliveryModel();
        deliveryModel.setOrderModel(orderRepository.findOne(orderApi.id));
        deliveryModel.setDeliveryStatus(DeliveryStatus.DRAFT);
        deliveryModel.setOfferedAmount(orderApi.offeredPrice);
        deliveryModel.setComments("Delivery is getting started by the SYSTEM");
        deliveryModel.setCreatedBy("SYSTEM");
        deliveryModel.setCreatedDate(new Date());
        deliveryModel.setCalculatedDistance(Long.valueOf(DeliveryProcessor.calculateDistance(orderApi)));
        deliveryModel.setRoutingModel(DeliveryProcessor.getRoutingModel(orderApi, deliveryModel));
        deliveryModel.setTimeOfArrival(new Date());
        routingRepository.save(deliveryModel.getRoutingModel());
        deliveryRepository.save(deliveryModel);
        return orderApi;
    }


}
