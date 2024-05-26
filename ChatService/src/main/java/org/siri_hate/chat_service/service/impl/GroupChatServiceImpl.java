package org.siri_hate.chat_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.repository.GroupChatRepository;
import org.siri_hate.chat_service.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChatServiceImpl implements GroupChatService {

    final private GroupChatRepository groupChatRepository;

    @Autowired
    public GroupChatServiceImpl(GroupChatRepository groupChatRepository) {
        this.groupChatRepository = groupChatRepository;
    }

    @Override
    @Transactional
    public void createGroupChat(CreateGroupChatRequest request) {
    }

}
