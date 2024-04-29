package org.siri_hate.notification_service.controller;

import jakarta.mail.MessagingException;
import org.siri_hate.notification_service.service.MailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
public class TestController {

    @Autowired
    private MailSenderServiceImpl mailSenderService;

    @PostMapping("/send/{email}")
    public void sendMail(@PathVariable String email, @RequestBody String name) throws MessagingException {
        mailSenderService.sendSuccessfulRegistrationMail(email, name);
    }

}
