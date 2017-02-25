package com.ca.order.dao.repositories;

import com.ca.order.dao.models.RoutingModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vivek.agrawal
 */
@Repository
public interface RoutingRepository extends PagingAndSortingRepository<RoutingModel, Long> {


}
