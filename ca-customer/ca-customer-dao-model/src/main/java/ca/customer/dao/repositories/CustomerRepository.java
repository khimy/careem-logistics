package ca.customer.dao.repositories;

import ca.customer.dao.models.CustomerModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vivek.agrawal
 */
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerModel, Long> {

}
