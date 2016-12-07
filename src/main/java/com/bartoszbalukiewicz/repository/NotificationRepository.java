package com.bartoszbalukiewicz.repository;

import com.bartoszbalukiewicz.model.Notification;
import com.bartoszbalukiewicz.model.view.NotificationView;
import com.bartoszbalukiewicz.model.view.TopicView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bartek on 06.12.2016.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT new com.bartoszbalukiewicz.model.view.NotificationView(n.time, n.source, n.ipAddress) FROM Notification n ORDER BY time ASC")
    List<NotificationView> getAll();
}
