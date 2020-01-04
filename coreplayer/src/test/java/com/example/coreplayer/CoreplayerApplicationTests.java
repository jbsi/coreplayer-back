package com.example.coreplayer;

import com.example.coreplayer.domain.Video;
import com.example.coreplayer.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootTest
class CoreplayerApplicationTests {
    @Autowired
    VideoService videoService;

    @Test
    void contextLoads() {
        List<Video> list=videoService.findByNameLike("声之形");
        System.out.println(list);
    }

}
