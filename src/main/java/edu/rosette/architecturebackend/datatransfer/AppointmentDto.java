package edu.rosette.architecturebackend.datatransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.rosette.architecturebackend.models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Appointment} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Date date;
    private Boolean online;
    private String consultationLink;
}