package edu.rosette.architecturebackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserRole {
    ROLE_DOCTOR,
    ROLE_MANAGER,
    ROLE_PATIENT
}
