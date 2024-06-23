package org.siri_hate.user_service.service.impl;

import jakarta.transaction.Transactional;
import org.hibernate.StaleObjectStateException;
import org.siri_hate.user_service.exception.NoSuchConfirmationTokenException;
import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.kafka.messages.ConfirmationMessage;
import org.siri_hate.user_service.model.entity.ConfirmationToken;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.enums.ConfirmationMessageType;
import org.siri_hate.user_service.model.enums.TokenType;
import org.siri_hate.user_service.repository.ConfirmationTokenRepository;
import org.siri_hate.user_service.service.ConfirmationService;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.ConcurrentModificationException;
import java.util.Optional;

/**
 * Confirmation service implementation class.
 * This class implements the ConfirmationService interface and provides the business logic for confirmation operations.
 * It uses the ConfirmationTokenRepository, MemberService, and KafkaProducerService to interact with the database, manage members, and send confirmation messages respectively.
 */
@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    // ConfirmationToken repository instance
    private final ConfirmationTokenRepository confirmationTokenRepository;

    // Member service instance
    private final MemberService memberService;

    // Kafka producer service instance
    private final KafkaProducerService kafkaProducerService;

    /**
     * Constructor for the ConfirmationServiceImpl class.
     * This constructor initializes the ConfirmationTokenRepository, MemberService, and KafkaProducerService.
     *
     * @param confirmationTokenRepository the confirmation token repository
     * @param memberService the member service
     * @param kafkaProducerService the Kafka producer service
     */
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

    /**
     * Generates a confirmation token.
     * This method generates a secure random key and encodes it to a string using Base64.
     *
     * @return the confirmation token
     */
    @Override
    public String generateConfirmationToken() {
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(32);
        byte[] key = keyGenerator.generateKey();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(key);
    }

    /**
     * Sends a registration confirmation.
     * This method generates a confirmation token, saves it to the database, and sends a confirmation message with the token.
     *
     * @param member the member to send the confirmation to
     */
    @Override
    public void sendRegistrationConfirmation(Member member) {
        ConfirmationMessageType messageType = ConfirmationMessageType.REGISTRATION_CONFIRMATION;
        String tokenValue = generateConfirmationToken();
        String tokenType = TokenType.CONFIRM_REGISTRATION.getValue();
        ConfirmationToken confirmationToken = new ConfirmationToken(tokenType, tokenValue, member);
        confirmationTokenRepository.save(confirmationToken);
        String confirmationTokenValue = confirmationToken.getTokenValue();
        ConfirmationMessage confirmationMessage = new ConfirmationMessage(
                messageType,
                member.getName(),
                member.getEmail(),
                confirmationTokenValue
        );
        kafkaProducerService.sendConfirmationToken(confirmationMessage);
    }

    /**
     * Sends a change password confirmation.
     * This method generates a confirmation token, saves it to the database, and sends a confirmation message with the token.
     *
     * @param member the member to send the confirmation to
     */
    @Override
    public void sendChangePasswordConfirmation(Member member) {
        ConfirmationMessageType messageType = ConfirmationMessageType.CHANGE_PASSWORD_CONFIRMATION;
        String tokenValue = generateConfirmationToken();
        String tokenType = TokenType.CONFIRM_CHANGE_PASSWORD.getValue();
        ConfirmationToken confirmationToken = new ConfirmationToken(tokenType, tokenValue, member);
        confirmationTokenRepository.save(confirmationToken);
        String confirmationTokenValue = confirmationToken.getTokenValue();
        ConfirmationMessage confirmationMessage = new ConfirmationMessage(
                messageType,
                member.getName(),
                member.getEmail(),
                confirmationTokenValue
        );
        kafkaProducerService.sendConfirmationToken(confirmationMessage);
    }

    /**
     * Checks a confirmation token.
     * This method retrieves a confirmation token from the database, activates the member account associated with the token, and deletes the token.
     *
     * @param token the confirmation token to check
     */
    @Override
    @Transactional
    public void checkConfirmationToken(String token) {

        try {
            Optional<ConfirmationToken> foundToken = confirmationTokenRepository.findConfirmationTokenByTokenValue(token);
            if (foundToken.isPresent()) {
                Long userId = foundToken.get().getMember().getId();
                memberService.activateMemberAccount(userId);
                confirmationTokenRepository.delete(foundToken.get());
            } else {
                throw new NoSuchConfirmationTokenException("Required confirmation token was not found");
            }
        } catch (StaleObjectStateException | ObjectOptimisticLockingFailureException e) {
            throw new ConcurrentModificationException(
                    "The confirmation token was modified or deleted by another transaction.");
        }

    }

    /**
     * Retrieves the user ID associated with a token.
     * This method retrieves a confirmation token from the database and returns the ID of the member associated with the token.
     *
     * @param token the confirmation token
     * @return the user ID
     */
    @Override
    public Long getUserIdByToken(String token) {

        Optional<ConfirmationToken> foundToken = confirmationTokenRepository.findConfirmationTokenByTokenValue(token);

        if (foundToken.isEmpty()) {
            throw new NoSuchConfirmationTokenException("Required confirmation token was not found");
        }

        return foundToken.get().getMember().getId();
    }

    /**
     * Deletes a confirmation token.
     * This method retrieves a confirmation token from the database and deletes it.
     *
     * @param token the confirmation token to delete
     */
    @Override
    public void deleteConfirmationTokenByTokenValue(String token) {

        Optional<ConfirmationToken> foundToken = confirmationTokenRepository.findConfirmationTokenByTokenValue(token);

        if (foundToken.isEmpty()) {
            throw new NoSuchConfirmationTokenException("Required confirmation token was not found");
        }

        ConfirmationToken confirmationToken = foundToken.get();
        confirmationTokenRepository.delete(confirmationToken);
    }

    /**
     * Finds a confirmation token.
     * This method retrieves a confirmation token from the database.
     *
     * @param token the confirmation token to find
     */
    @Override
    public void findConfirmationTokenByTokenValue(String token) {
        Optional<ConfirmationToken> foundToken = confirmationTokenRepository.findConfirmationTokenByTokenValue(token);
        if (foundToken.isEmpty()) {
            throw new NoSuchConfirmationTokenException("Required confirmation token was not found");
        }
    }

}