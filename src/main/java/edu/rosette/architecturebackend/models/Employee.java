package edu.rosette.architecturebackend.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
