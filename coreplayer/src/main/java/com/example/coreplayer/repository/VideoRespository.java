package com.example.coreplayer.repository;

import com.example.coreplayer.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRespository extends BaseRepository<Video,Integer>{
    List<Video> findByNameLike(String namelike);
}
