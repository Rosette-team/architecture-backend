package edu.rosette.architecturebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Employee extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
