package com.lio.chatserver.model.repo;

import com.lio.chatserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User,Integer>  {

}