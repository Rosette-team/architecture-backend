package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Message;
import edu.rosette.architecturebackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllBySenderAndReceiver(User sender, User receiver);
}
