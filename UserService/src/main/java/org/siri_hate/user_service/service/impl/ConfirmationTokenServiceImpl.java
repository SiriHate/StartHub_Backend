package org.siri_hate.user_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.NoSuchConfirmationTokenException;
import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.model.entity.ConfirmationToken;
import org.siri_hate.user_service.repository.ConfirmationTokenRepository;
import org.siri_hate.user_service.service.ConfirmationTokenService;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private  final ConfirmationTokenRepository confirmationTokenRepository;

    private  final MemberService memberService;

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    private ConfirmationTokenServiceImpl(
            ConfirmationTokenRepository confirmationTokenRepository,
            MemberService memberService,
            KafkaProducerService kafkaProducerService
    ) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.memberService = memberService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void sendConfirmationToken(Long userId, String name, String email) {
        ConfirmationToken confirmationToken = new ConfirmationToken(userId, name, email);
        confirmationTokenRepository.save(confirmationToken);
        kafkaProducerService.sendConfirmationToken(confirmationToken);
    }

    @Override
    @Transactional
    public void checkMemberConfirmationToken(String token) {
        ConfirmationToken foundToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (foundToken != null) {
            memberService.activateMemberAccount(foundToken.getUserId());
            confirmationTokenRepository.delete(foundToken);
        } else {
            throw new NoSuchConfirmationTokenException("Required confirmation token was not found");
        }
    }

}
