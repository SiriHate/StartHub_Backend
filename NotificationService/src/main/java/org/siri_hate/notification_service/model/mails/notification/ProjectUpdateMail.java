package org.siri_hate.notification_service.model.mails.notification;

import org.siri_hate.notification_service.model.mails.MailTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

public class ProjectUpdateMail extends MailTemplate {
    private final String userFullName;
    private final String projectName;
    private final String projectLink;
    private final String updateDate;

    public ProjectUpdateMail(String userEmail, String userFullName, String projectName, String projectLink, String updateDate) {
        this.toEmailAddress = userEmail;
        this.userFullName = userFullName;
        this.projectName = projectName;
        this.projectLink = projectLink;
        this.updateDate = updateDate;
        this.subject = "Обновление проекта " + projectName;
    }

    @Override
    public void prepareMessage(Context context, TemplateEngine templateEngine) {
        context.setVariable("userFullName", userFullName);
        context.setVariable("projectName", projectName);
        context.setVariable("projectLink", projectLink);
        context.setVariable("updateDate", updateDate);
        this.message = templateEngine.process("project-update-mail", context);
    }
}