package com.example.coreplayer.repository;

import com.example.coreplayer.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer>{
    User findByUsernameAndPassword(String username,String password);
}
