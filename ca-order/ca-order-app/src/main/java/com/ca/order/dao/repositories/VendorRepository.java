package com.ca.order.dao.repositories;

import com.ca.order.dao.models.VendorModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vivek.agrawal
 */
@Repository
public interface VendorRepository extends PagingAndSortingRepository<VendorModel, Long> {


}
