package edu.rosette.architecturebackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String surname;
}
