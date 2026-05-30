package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.training.api.Training;

/**
 * Training persistence with LAB03 native query for aggregated distance.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Returns total distance (km) covered by the given user across all trainings.
     */
    @Query(
            value = "SELECT COALESCE(SUM(distance), 0) FROM trainings WHERE user_id = :userId",
            nativeQuery = true
    )
    double sumDistanceByUserId(@Param("userId") Long userId);

}
