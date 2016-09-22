package com.bartoszbalukiewicz.repository;

import com.bartoszbalukiewicz.model.Message;
import com.bartoszbalukiewicz.model.Topic;
import com.bartoszbalukiewicz.model.view.TopicView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by postgres on 2016-09-20.
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    @Query("SELECT new com.bartoszbalukiewicz.model.view.TopicView(t.title, t.description, t.author) FROM Topic t ORDER BY created DESC")
    List<TopicView> getAll();
}
