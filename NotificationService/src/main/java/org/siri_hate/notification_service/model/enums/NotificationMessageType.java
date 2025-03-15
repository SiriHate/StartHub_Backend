package org.siri_hate.notification_service.model.enums;

import lombok.Getter;

@Getter
public enum NotificationMessageType {
  SUCCESSFUL_REGISTRATION_NOTIFICATION,

  DELETED_ACCOUNT_NOTIFICATION,

  CHANGED_PASSWORD_NOTIFICATION;
}
