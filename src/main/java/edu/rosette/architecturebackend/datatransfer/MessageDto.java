package edu.rosette.architecturebackend.datatransfer;

import edu.rosette.architecturebackend.models.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Message} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto implements Serializable {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private Date date;
    private String text;
}