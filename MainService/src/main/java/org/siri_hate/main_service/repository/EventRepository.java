package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
