package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.form.MessageForm;
import com.bartoszbalukiewicz.form.TopicForm;
import com.bartoszbalukiewicz.model.Message;
import com.bartoszbalukiewicz.model.Topic;
import com.bartoszbalukiewicz.model.view.TopicView;
import com.bartoszbalukiewicz.repository.MessageRepository;
import com.bartoszbalukiewicz.repository.TopicRepository;
import com.bartoszbalukiewicz.security.SecurityUtils;
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

    @Autowired
    private UserService userService;

    public Topic createTopic(TopicForm form) {
        Topic topic = new Topic();
        topic.setTitle(form.getTitle());
        topic.setDescription(form.getDescription());
        topic.setAuthor(SecurityUtils.getAuthenticatedUserName());

        topicRepository.save(topic);
        return topic;
    }

    public List<TopicView> getAll() {
        return topicRepository.getAll();
    }

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
