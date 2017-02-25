package ca.customer.dao.repositories;

import ca.customer.dao.models.EntityModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vivek.agrawal
 */
@Repository
public interface EntityRepository extends PagingAndSortingRepository<EntityModel, Long> {

}
