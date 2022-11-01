package edu.rosette.architecturebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User patient;

    @ManyToOne(fetch = FetchType.LAZY)
    Employee doctor;

    Boolean online;
}
