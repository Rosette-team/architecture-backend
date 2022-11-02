package edu.rosette.architecturebackend.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Doctor extends Employee {
}
