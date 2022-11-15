package edu.rosette.architecturebackend.datatransfer;

import edu.rosette.architecturebackend.models.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Message} entity
 */
@AllArgsConstructor
@Data
public class MessageDto implements Serializable {
    private final Long id;
    private Long senderId;
    private Long receiverId;
    private final Date date;
    private final String text;
}