package org.siri_hate.user_service.service;

import com.google.gson.Gson;
import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.kafka.messages.ProjectUpdateNotification;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectUpdateNotificationConsumerService {

    private final MemberRepository memberRepository;
    private final KafkaProducerService kafkaProducerService;
    private final Gson gson = new Gson();

    @Autowired
    public ProjectUpdateNotificationConsumerService(
            MemberRepository memberRepository,
            KafkaProducerService kafkaProducerService) {
        this.memberRepository = memberRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "${project.update.notification.topic.consumer}", groupId = "${spring.application.name}")
    @Transactional
    public void consumeProjectUpdateNotification(String message) {
        ProjectUpdateNotification notification = gson.fromJson(message, ProjectUpdateNotification.class);
        String username = notification.getUsername();
        Member member = memberRepository.findMemberByUsername(username);
        if (member != null) {
            notification.setUserRealName(member.getName());
            notification.setUserEmailAddress(member.getEmail());
        }
        kafkaProducerService.sendProjectUpdateNotification(notification);
    }
} 