package edu.rosette.architecturebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity()
@Table(name = "working_windows")
@Getter
@Setter
public class WorkingWindow {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Doctor doctor;

    @Column(nullable = false)
    LocalDate beginDate;

    LocalDate endDate;

    @Column(nullable = false)
    DayOfWeek dayOfWeek;

    @Column(nullable = false)
    LocalTime beginTime;

    @Column(nullable = false)
    LocalTime endTime;
}
