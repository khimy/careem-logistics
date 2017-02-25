package org.sa.notification.dao.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NotificationModel.class)
public abstract class NotificationModel_ extends org.sa.notification.dao.models.AbstractPersistable_ {

	public static volatile SingularAttribute<NotificationModel, NotificationAction> notificationAction;
	public static volatile SingularAttribute<NotificationModel, NotificationStatus> notificationStatus;
	public static volatile SingularAttribute<NotificationModel, String> notificationMessage;
	public static volatile SingularAttribute<NotificationModel, String> options;
	public static volatile SingularAttribute<NotificationModel, String> notificationTo;
	public static volatile SingularAttribute<NotificationModel, NotificationType> notificationType;
	public static volatile SingularAttribute<NotificationModel, Long> attempts;

}

