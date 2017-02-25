package com.ca.order.services;

import com.ca.order.api.Address;
import com.ca.order.api.OrderApi;
import com.ca.order.dao.models.AddressModel;
import com.ca.order.dao.models.OrderModel;
import com.ca.order.dao.models.Status;
import com.ca.order.dao.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivek on 19-01-2017.
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderApi createOrder(Long customerId, OrderApi orderApi) {
        OrderModel orderModel=toOrderModel(orderApi);
        orderModel.setCustomerModel(orderRepository.findCustomer(customerId).get(0));
        return toOrderApi(orderRepository.save(orderModel));
    }

    public OrderApi getOrder(Long orderId) {
        OrderModel orderModel=orderRepository.findOne(orderId);
        if(orderModel==null){
            return null;
        }
        return toOrderApi(orderModel);
    }

    public List<OrderApi> getOrderList(Long customerId) {
        List<OrderApi> orderApiList=new ArrayList<>();
        List<OrderModel> orderModels=orderRepository.findByCustomerId(customerId);
        if(orderModels==null || orderModels.isEmpty()){
            return null;
        }
        for(OrderModel orderModel:orderModels){
            OrderApi orderApi=toOrderApi(orderModel);
            orderApiList.add(orderApi);
        }
        return orderApiList;
    }

    @Transactional
    public OrderApi updateOrder(Long customerId, OrderApi orderApi) {
        OrderModel orderModel=orderRepository.findOne(orderApi.id);
        orderModel.setOfferedPrice(orderApi.offeredPrice);
        orderModel.setQuotedPrice(orderApi.quotedPrice);
        orderModel.setStatus(toStatusModel(orderApi.status));
        orderModel.setQuantity(orderApi.quantity);
        orderModel.setComments(orderApi.comments);
        return toOrderApi(orderRepository.save(orderModel));
    }

    @Transactional
    public OrderApi approveOrder(Long customerId, OrderApi orderApi) {
        OrderModel orderModel=orderRepository.findOne(orderApi.id);
        orderModel.setOfferedPrice(orderApi.offeredPrice);
        orderModel.setQuotedPrice(orderApi.quotedPrice);
        orderModel.setStatus(Status.APPROVED);
        orderModel.setQuantity(orderApi.quantity);
        orderModel.setComments(orderApi.comments);
        return toOrderApi(orderRepository.save(orderModel));
    }


    private OrderModel toOrderModel(OrderApi orderApi) {
        OrderModel orderModel=new OrderModel();
        orderModel.setQuotedPrice(orderApi.quotedPrice);
        orderModel.setStatus(toStatusModel(orderApi.status));
        orderModel.setComments(orderApi.comments);
        orderModel.setDropAddress(toAddressModel(orderApi.addressA));
        orderModel.setPickUpAddress(toAddressModel(orderApi.addressB));
        orderModel.setProductDescription(orderApi.productDescription);
        orderModel.setQuantity(orderApi.quantity);
        orderModel.setWeight(orderApi.weight);
        return  orderModel;
    }

    private OrderApi toOrderApi(OrderModel order) {
        OrderApi orderApi=new OrderApi();
        orderApi.id=order.getId();
        orderApi.customerId=order.getCustomerModel().getId();
        orderApi.quotedPrice=order.getQuotedPrice();
        orderApi.status=(toStatus(order.getStatus()));
        orderApi.comments=order.getComments();
        orderApi.addressA=toAddressApi(order.getDropAddress());
        orderApi.addressB=toAddressApi(order.getPickUpAddress());
        orderApi.productDescription=order.getProductDescription();
        orderApi.quantity=order.getQuantity();
        orderApi.offeredPrice=order.getOfferedPrice();
        order.setWeight(orderApi.weight);
        return  orderApi;
    }


    private AddressModel toAddressModel(Address address) {
        AddressModel addressModel=new AddressModel();
        addressModel.setAddressLine1(address.addressLine1);
        addressModel.setAddressLine2(address.addressLine2);
        addressModel.setPostalAddress(address.postalAddress);
        addressModel.setCity(address.city);
        addressModel.setCountry(address.country);
        addressModel.setState(address.state);
        return addressModel;
    }

    private Address toAddressApi (AddressModel address) {
        Address addressApi=new Address();
        addressApi.city=address.getCity();
        addressApi.state=address.getState();
        addressApi.country=address.getCountry();
        addressApi.addressLine1=address.getAddressLine1();
        addressApi.addressLine2=address.getAddressLine2();
        addressApi.postalAddress=address.getPostalAddress();
        return addressApi;
    }


    private com.ca.order.api.Status toStatus(Status status) {
        com.ca.order.api.Status statusApi=null;

        switch (status){
            case DRAFT:
                statusApi=com.ca.order.api.Status.DRAFT;
                break;
            case APPROVED:
                statusApi=com.ca.order.api.Status.APPROVED;
                break;
            case CLOSED:
                statusApi=com.ca.order.api.Status.CLOSED;
                break;
            case PENDING:
                statusApi=com.ca.order.api.Status.PENDING;
                break;
            case REJECTED:
                statusApi=com.ca.order.api.Status.PENDING;
                break;
        }
        return statusApi;
    }

    private Status toStatusModel(com.ca.order.api.Status status) {
        Status statusModel=null;
        switch (status){
            case DRAFT:
                statusModel=Status.DRAFT;
                break;
            case APPROVED:
                statusModel=Status.APPROVED;
                break;
            case CLOSED:
                statusModel=Status.CLOSED;
                break;
            case PENDING:
                statusModel=Status.PENDING;
                break;
            case REJECTED:
                statusModel=Status.PENDING;
                break;
        }
        return statusModel;
    }
}
