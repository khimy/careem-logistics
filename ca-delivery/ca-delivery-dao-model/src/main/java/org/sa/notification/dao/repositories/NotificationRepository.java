package org.sa.notification.dao.repositories;

import org.sa.notification.dao.models.NotificationModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vivek.agrawal
 */
@Repository
public interface NotificationRepository extends PagingAndSortingRepository<NotificationModel, Long> {

}
