package edu.rosette.architecturebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Temporal(TemporalType.TIMESTAMP)
    Date beginDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;

    @Column(nullable = false)
    String periodicity;
}
