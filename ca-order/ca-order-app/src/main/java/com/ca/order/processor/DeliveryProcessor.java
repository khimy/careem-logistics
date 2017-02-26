package com.ca.order.processor;

import com.ca.order.api.OrderApi;
import com.ca.order.api.RoutingMode;
import com.ca.order.api.RoutingStatus;
import com.ca.order.dao.models.DeliveryModel;
import com.ca.order.dao.models.RoutingModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Vivek on 26-02-2017.
 */
public class DeliveryProcessor {

    /**
     * any number between 500 to 2000
     * @param orderApi
     * @return
     */
    public static int calculateDistance(OrderApi orderApi) {
        int max=2000;
        int min=500;
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }

    public static List<RoutingModel> getRoutingModel(OrderApi orderApi, DeliveryModel deliveryModel) {
        List<RoutingModel> routingModels=new ArrayList<>();
        if(deliveryModel.getCalculatedDistance()>1000){
            routingModels.add(createRoute(RoutingMode.SHIP,deliveryModel.getCalculatedDistance()));
            routingModels.add(createRoute(RoutingMode.TRUCK,deliveryModel.getCalculatedDistance()-1000));
        }else{
            routingModels.add(createRoute(RoutingMode.TRUCK, deliveryModel.getCalculatedDistance()));
        }
        return routingModels;
    }

    public static RoutingModel createRoute(RoutingMode mode, Long calculatedDistance){
        RoutingModel routingModel=new RoutingModel();
        routingModel.setRoutingStatus(RoutingStatus.AWAITING_PARTICIPATION);
        routingModel.setCreatedBy("SYSTEM");
        routingModel.setCreatedDate(new Date());
        routingModel.setEta(8L);
        routingModel.setModeOfCommunication(mode);
        routingModel.setEstimatedDistance(calculatedDistance);
        routingModel.setStartDate(new Date());
        return routingModel;
    }
}
