package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.form.MessageForm;
import com.bartoszbalukiewicz.form.TopicForm;
import com.bartoszbalukiewicz.model.Message;
import com.bartoszbalukiewicz.model.Topic;
import com.bartoszbalukiewicz.repository.MessageRepository;
import com.bartoszbalukiewicz.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by postgres on 2016-09-20.
 */
@Service
public class ForumService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageRepository messageRepository;

   /* public List<Topic> getTopis() {
        return topicRepository.findAll();
    }

    public List<Message> getMessagesForTopic(Long topicId) {
        return messageRepository.getMessagesByTopicId(topicId);
    }

    public Topic createTopic(TopicForm form) {

    }

    public Topic editTopic(Long topicId, TopicForm form) {

    }

    public Message createMessage(Long topicId, MessageForm form) {

    }

    public Message editMessage(Long messageId, MessageForm form) {

    }*/

}
