package edu.rosette.architecturebackend.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    User receiver;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    @NotNull
    String text;
}
