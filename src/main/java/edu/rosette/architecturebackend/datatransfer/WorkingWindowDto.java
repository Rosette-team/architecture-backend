package edu.rosette.architecturebackend.datatransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.rosette.architecturebackend.models.WorkingWindow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

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
    private Date beginDate;
    private Date endDate;
    private String periodicity;
    private Duration duration;
}