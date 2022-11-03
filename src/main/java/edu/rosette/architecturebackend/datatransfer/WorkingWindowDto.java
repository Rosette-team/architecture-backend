package edu.rosette.architecturebackend.datatransfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.rosette.architecturebackend.models.WorkingWindow;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link WorkingWindow} entity
 */
@Data
public class WorkingWindowDto implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    private Long doctorId;
    private final Date beginDate;
    private final Date endDate;
    private final String periodicity;
}