package org.siri_hate.chat_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.chat_service.model.PersonalChat;
import org.siri_hate.chat_service.model.dto.mapper.PersonalChatMapper;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.repository.PersonalChatRepository;
import org.siri_hate.chat_service.service.PersonalChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalChatServiceImpl implements PersonalChatService {

    final private PersonalChatRepository personalChatRepository;

    final private PersonalChatMapper personalChatMapper;

    @Autowired
    public PersonalChatServiceImpl(
            PersonalChatRepository personalChatRepository,
            PersonalChatMapper personalChatMapper
                                  ) {
        this.personalChatRepository = personalChatRepository;
        this.personalChatMapper = personalChatMapper;
    }

    @Override
    @Transactional
    public void createPersonalChat(CreatePersonalChatRequest request) {
        PersonalChat personalChatEntity = personalChatMapper.toPersonalChatFromRequest(request);
        personalChatRepository.save(personalChatEntity);
    }

}
