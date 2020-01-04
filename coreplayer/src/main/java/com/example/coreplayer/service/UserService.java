package com.example.coreplayer.service;

import com.example.coreplayer.domain.User;
import com.example.coreplayer.repository.UserRepository;

public interface UserService extends BaseService<User,Integer, UserRepository> {
    /**
     * 判断用户是否存在于数据库，且用户名密码匹配
     * @param username,password
     * @return
     */
    User login(String username,String password);
}
