package edu.rosette.architecturebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    Doctor doctor;

    @Column(nullable = false)
    LocalDate date;

    @Column(nullable = false)
    LocalTime time;

    @Column(nullable = false)
    Boolean online;

    @Column(nullable = true)
    String consultationLink;
}
