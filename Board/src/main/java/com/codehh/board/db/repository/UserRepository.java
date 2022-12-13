package com.codehh.board.db.repository;

import com.codehh.board.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(String id);

    User findByNickname(String nickname);

}
