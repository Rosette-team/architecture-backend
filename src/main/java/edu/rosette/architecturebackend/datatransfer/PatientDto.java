package edu.rosette.architecturebackend.datatransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.rosette.architecturebackend.models.Patient;
import edu.rosette.architecturebackend.models.UserRole;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Patient} entity
 */
@Data
public class PatientDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    private final String name;
    private final String surname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final UserRole role;
}