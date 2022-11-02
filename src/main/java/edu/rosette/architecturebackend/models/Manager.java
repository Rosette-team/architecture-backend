package edu.rosette.architecturebackend.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Manager extends Employee {
}
