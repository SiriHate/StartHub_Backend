package org.siri_hate.user_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.NoSuchConfirmationTokenException;
import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.model.entity.ConfirmationToken;
import org.siri_hate.user_service.model.enums.ConfirmationMessageType;
import org.siri_hate.user_service.repository.ConfirmationTokenRepository;
import org.siri_hate.user_service.service.ConfirmationService;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private  final ConfirmationTokenRepository confirmationTokenRepository;

    private  final MemberService memberService;

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    private ConfirmationServiceImpl(
            ConfirmationTokenRepository confirmationTokenRepository,
            MemberService memberService,
            KafkaProducerService kafkaProducerService
    ) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.memberService = memberService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void sendMemberConfirmRegistration(Long userId, String name, String email) {
        ConfirmationMessageType messageType = ConfirmationMessageType.REGISTRATION_CONFIRMATION;
        ConfirmationToken confirmationToken = new ConfirmationToken(userId, name, email);
        confirmationToken.setTokenType("ConfirmMemberRegistrationToken");
        confirmationTokenRepository.save(confirmationToken);
        String confirmationTokenValue = confirmationToken.getTokenValue();
        ConfirmationMessage confirmationMessage = new ConfirmationMessage(messageType, name, email, confirmationTokenValue);
        kafkaProducerService.sendConfirmationToken(confirmationMessage);
    }

    @Override
    @Transactional
    public void checkMemberConfirmationToken(String token) {
        ConfirmationToken foundToken = confirmationTokenRepository.findConfirmationTokenByTokenValue(token);
        if (foundToken != null) {
            memberService.activateMemberAccount(foundToken.getUserId());
            confirmationTokenRepository.delete(foundToken);
        } else {
            throw new NoSuchConfirmationTokenException("Required confirmation token was not found");
        }
    }

}
