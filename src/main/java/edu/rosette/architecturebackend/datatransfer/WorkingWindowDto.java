package edu.rosette.architecturebackend.datatransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.rosette.architecturebackend.models.WorkingWindow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A DTO for the {@link WorkingWindow} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkingWindowDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long doctorId;
    private LocalDate beginDate;
    private LocalDate endDate;
    private DayOfWeek dayOfWeek;
    private LocalTime beginTime;
    private LocalTime endTime;
}