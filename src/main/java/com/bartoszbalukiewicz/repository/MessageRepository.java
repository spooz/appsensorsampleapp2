package com.bartoszbalukiewicz.repository;

import com.bartoszbalukiewicz.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by postgres on 2016-09-20.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.topic.id = :topicId")
    List<Message> getMessagesByTopicId(@Param(value = "topicId") Long topicId);

}
