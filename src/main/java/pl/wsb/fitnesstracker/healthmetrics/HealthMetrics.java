package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.*;
import lombok.Getter;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "Health_Metrics")
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "heartRate")
    private Integer heartRate;


public HealthMetrics(
        final Long id,
        final User user,
        final LocalDate date,
        final Double weight,
        final Double height,
        final Integer heartRate
        ) {

    this.user = user;
    this.id = id;
    this.date= date;
    this.weight = weight;
    this.height = height;
    this.heartRate = heartRate;
}
}