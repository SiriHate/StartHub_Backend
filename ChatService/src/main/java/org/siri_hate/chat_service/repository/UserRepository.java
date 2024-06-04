package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    List<User> findAllById(Iterable<String> ids);
}
