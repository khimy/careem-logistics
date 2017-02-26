package com.ca.order.dao.repositories;

import com.ca.order.dao.models.CustomerModel;
import com.ca.order.dao.models.DeliveryModel;
import com.ca.order.dao.models.OrderModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vivek.agrawal
 */
@Repository
public interface DeliveryRepository extends PagingAndSortingRepository<DeliveryModel, Long> {

    List<DeliveryModel> findByOrderModel(OrderModel orderModel);


}
