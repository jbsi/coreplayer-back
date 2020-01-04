package com.example.coreplayer.service.impl;

import com.example.coreplayer.domain.User;
import com.example.coreplayer.repository.UserRepository;
import com.example.coreplayer.service.BaseService;
import com.example.coreplayer.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User,Integer, UserRepository> implements UserService {
    @Override
    public User  login(String username,String password) {
        User  user =dao.findByUsernameAndPassword(username, password);
        return user;
    }
}
