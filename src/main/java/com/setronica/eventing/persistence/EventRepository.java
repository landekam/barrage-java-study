package com.setronica.eventing.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM #{#entityName} e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :searchQuery, '%'))")
    List<Event> searchByTitle(@Param("searchQuery") String searchQuery);
}
