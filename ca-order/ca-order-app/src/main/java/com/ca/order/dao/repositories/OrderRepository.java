package com.ca.order.dao.repositories;

import com.ca.customer.dao.models.CustomerModel;
import com.ca.order.dao.models.OrderModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;
import java.util.List;

/**
 * @author vivek.agrawal
 */
@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderModel, Long> {

    List<OrderModel> findByCustomer(CustomerModel customerModel);

    @Query("SELECT p FROM OrderModel p WHERE LOWER(p.customerId) = :customerId")
    public List<OrderModel> findByCustomerId(@Param("customerId") Long customerId);
}
