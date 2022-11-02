package edu.rosette.architecturebackend.datatransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.rosette.architecturebackend.models.Appointment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Appointment} entity
 */
@Data
public class AppointmentDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    private Long patientId;
    private Long doctorId;
    private final Date date;
    private final Boolean online;
    private final String consultationLink;
}