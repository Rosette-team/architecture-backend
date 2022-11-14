package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Message;
import edu.rosette.architecturebackend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender = :firstUser AND m.receiver = :secondUser) OR (m.receiver = :firstUser AND m.sender = :secondUser)")
    List<Message> findAllBetweenUsers(User firstUser, User secondUser);
}
