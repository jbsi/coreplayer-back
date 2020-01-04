package com.example.coreplayer.service.impl;

import com.example.coreplayer.domain.Video;
import com.example.coreplayer.repository.VideoRespository;
import com.example.coreplayer.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl extends BaseServiceImpl<Video,Integer, VideoRespository> implements VideoService{

    @Override
    public List<Video> findByNameLike(String namelike) {
        return dao.findByNameLike("%"+namelike+"%");
    }
}

