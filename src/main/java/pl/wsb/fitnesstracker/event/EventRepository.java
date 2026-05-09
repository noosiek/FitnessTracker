package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Event queries supporting LAB03 read use cases.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Returns events that start after the provided date.
     */
    @Query("SELECT e FROM Event e WHERE e.startDate > :now ORDER BY e.startDate")
    List<Event> findUpcoming(@Param("now") LocalDate now);

    /**
     * Counts registered users for a single event.
     */
    @Query(
            value = "SELECT COUNT(*) FROM user_event WHERE event_id = :eventId",
            nativeQuery = true
    )
    long countParticipants(@Param("eventId") Long eventId);
}
