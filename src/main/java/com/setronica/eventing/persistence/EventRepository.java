package com.setronica.eventing.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {
    Optional<Event> findById(int id);

    List<Event> findAll();
}
