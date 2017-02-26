package com.ca.order.services;

import com.ca.order.api.DeliveryDetails;
import com.ca.order.api.RouteDetails;
import com.ca.order.api.RoutingStatus;
import com.ca.order.dao.models.DeliveryModel;
import com.ca.order.dao.models.OrderModel;
import com.ca.order.dao.models.RoutingModel;
import com.ca.order.dao.repositories.DeliveryRepository;
import com.ca.order.dao.repositories.OrderRepository;
import com.ca.order.dao.repositories.RoutingRepository;
import com.ca.order.exception.InvalidOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vivek on 26-02-2017.
 */
@Component
public class DeliveryService {
    private DeliveryRepository deliveryRepository;
    private RoutingRepository routingRepository;
    private OrderRepository orderRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository,RoutingRepository routingRepository,OrderRepository orderRepository){
        this.deliveryRepository=deliveryRepository;
        this.routingRepository=routingRepository;
        this.orderRepository=orderRepository;
    }

    @Transactional
    public DeliveryDetails createDelivery(Long orderId, DeliveryDetails deliveryDetails) {
        OrderModel orderModel=orderRepository.findOne(orderId);
        if(orderModel==null){
            throw new InvalidOrderRequest("Invalid Order");
        }
        DeliveryModel deliveryModel=toDeliveryModel(deliveryDetails);
        deliveryRepository.save(deliveryModel);
        return toDeliveryAPI(deliveryModel);
    }

    private DeliveryDetails toDeliveryAPI(DeliveryModel m) {
        DeliveryDetails d=new DeliveryDetails();
        d.calculatedDistance=m.getCalculatedDistance();
        d.comments=m.getComments();
        d.deliveryStatus=m.getDeliveryStatus();
        d.id=m.getId();
        d.offeredSum=m.getOfferedAmount();
        d.orderId=m.getOrderModel().getId();
        d.timeOfArrival=m.getTimeOfArrival();
        return d;
    }

    private DeliveryModel toDeliveryModel(DeliveryDetails d) {
        DeliveryModel m=new DeliveryModel();
        m.setCalculatedDistance(d.calculatedDistance);
        m.setComments(d.comments);
        m.setDeliveryStatus(d.deliveryStatus);
        m.setOfferedAmount(d.offeredSum);
        m.setOrderModel(orderRepository.findOne(d.orderId));
        m.setTimeOfArrival(d.timeOfArrival);
        return m;
    }

    @Transactional
    public DeliveryDetails updateDelivery(Long orderId, DeliveryDetails deliveryDetails) {
        DeliveryModel m=deliveryRepository.findOne(deliveryDetails.id);
        m.setCalculatedDistance(deliveryDetails.calculatedDistance);
        m.setComments(deliveryDetails.comments);
        m.setDeliveryStatus(deliveryDetails.deliveryStatus);
        m.setOfferedAmount(deliveryDetails.offeredSum);
        m.setTimeOfArrival(deliveryDetails.timeOfArrival);
        return toDeliveryAPI(deliveryRepository.save(m));
    }

    public DeliveryDetails getDelivery(Long deliveryId) {
        return toDeliveryAPI(deliveryRepository.findOne(deliveryId));
    }

    public List<DeliveryDetails> getAllDelivery(Long orderId) {
        List<DeliveryDetails> ddList=new ArrayList<>();
        OrderModel orderModel=orderRepository.findOne(orderId);
        List<DeliveryModel> dm=deliveryRepository.findByOrderModel(orderModel);
        if(dm!=null && !dm.isEmpty()){
            for(DeliveryModel d:dm){
                ddList.add(toDeliveryAPI(d));
            }
        }
        return ddList;
    }

    @Transactional
    public RouteDetails updateRoute(Long deliveryId, RouteDetails routeDetails) {
        DeliveryModel deliveryModel=deliveryRepository.findOne(deliveryId);
        RoutingModel routingModel=routingRepository.findOne(routeDetails.id);
        routingModel.setRoutingStatus(routeDetails.routingStatus);
        if(RoutingStatus.CHECKED_OUT.equals(routeDetails.routingStatus)){
            routingModel.setEndDate(new Date());
        }
        routingModel.setEstimatedEndDate(routeDetails.estimatedEndDate);
        return toRouteApi(routingRepository.save(routingModel));
    }

    private RouteDetails toRouteApi(RoutingModel model) {
        RouteDetails routeDetails=new RouteDetails();
        routeDetails.id=model.getId();
        routeDetails.routingStatus=model.getRoutingStatus();
        routeDetails.estimatedEndDate=model.getEstimatedEndDate();
        routeDetails.vendorId=model.getVendorModel().getId();
        routeDetails.isParticipated=model.isParticipated();
        routeDetails.mobile=model.getMobile();
        routeDetails.eta=model.getEta();
        return routeDetails;
    }


    public RouteDetails getRoute(Long routingId) {
        return toRouteApi(routingRepository.findOne(routingId));
    }


    public List<RouteDetails> getAllRoute(Long deliveryId) {
        List<RouteDetails> rdList=new ArrayList<>();
        Iterable<RoutingModel> routes=routingRepository.findAll();
        if(routes==null){
            return null;
        }
        for(RoutingModel routingModel:routes){
            rdList.add(toRouteApi(routingModel));
        }
        return rdList;
    }
}
