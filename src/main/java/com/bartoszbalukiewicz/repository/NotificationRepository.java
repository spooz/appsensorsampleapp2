package com.bartoszbalukiewicz.repository;

import com.bartoszbalukiewicz.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bartek on 06.12.2016.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
