package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.form.MessageForm;
import com.bartoszbalukiewicz.form.TopicForm;
import com.bartoszbalukiewicz.model.Message;
import com.bartoszbalukiewicz.model.Topic;
import com.bartoszbalukiewicz.model.view.MessageView;
import com.bartoszbalukiewicz.model.view.NotificationView;
import com.bartoszbalukiewicz.model.view.TopicView;
import com.bartoszbalukiewicz.repository.MessageRepository;
import com.bartoszbalukiewicz.repository.NotificationRepository;
import com.bartoszbalukiewicz.repository.TopicRepository;
import com.bartoszbalukiewicz.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;

    @Transactional
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

    public Topic findById(Long id) { return topicRepository.findOne(id);
    }

    @PreAuthorize("@securityService.isAdmin()")
    public void deleteTopic(Long id) {
        topicRepository.delete(id);
    }

    @PreAuthorize("@securityService.isAdmin()")
    public void deleteMessage(Long id) { messageRepository.delete(id);}

    @PreAuthorize("@securityService.isAdmin()")
    public List<NotificationView> getNotifications() {
        return notificationRepository.getAll();
    }

    //TODO: CHECH IF TOPIC EXSITS
    public List<MessageView> getMessagesForTopic(Long topicId) {
        return messageRepository.getMessagesByTopicId(topicId);
    }

    @Transactional
    public Message createMessage(MessageForm form, Long topicId) {
        Topic topic = topicRepository.findOne(topicId);
        Message message = new Message();
        message.setTitle(form.getTitle());
        message.setMessage(form.getMessage());
        message.setAuthor(SecurityUtils.getAuthenticatedUserName());
        message.setTopic(topic);

        messageRepository.save(message);

        return message;
    }
}
