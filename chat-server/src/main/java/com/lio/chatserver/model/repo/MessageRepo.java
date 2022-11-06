package com.lio.chatserver.model.repo;

import com.lio.chatserver.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("messageRepo")
public interface MessageRepo extends JpaRepository<Message,Integer> {

}