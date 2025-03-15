package org.siri_hate.notification_service.model.mails;

import lombok.Data;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Data
public abstract class MailTemplate {

  protected String toEmailAddress;

  protected String subject;

  protected String message;

  public abstract void prepareMessage(Context context, TemplateEngine templateEngine);
}
