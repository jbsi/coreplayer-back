package com.example.coreplayer.service;

import com.example.coreplayer.domain.BaseEntity;
import com.example.coreplayer.domain.User;
import com.example.coreplayer.domain.Video;
import com.example.coreplayer.repository.UserRepository;
import com.example.coreplayer.repository.VideoRespository;

import java.util.List;

public interface VideoService extends BaseService<Video,Integer, VideoRespository>  {
    List<Video> findByNameLike(String namelike);
}
