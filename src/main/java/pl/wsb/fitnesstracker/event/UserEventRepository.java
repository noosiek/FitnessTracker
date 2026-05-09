package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Access methods for user-to-event registration records.
 */
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    /**
     * Counts how many users are registered for the given event.
     */
    @Query(
            value = "SELECT COUNT(*) FROM user_event WHERE event_id = :eventId",
            nativeQuery = true
    )
    long countParticipants(@Param("eventId") Long eventId);
}
