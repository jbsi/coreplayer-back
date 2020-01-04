package com.example.coreplayer.controller;

import com.example.coreplayer.domain.Video;
import com.example.coreplayer.service.VideoService;
import com.example.coreplayer.util.Json;
import io.netty.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("video")
public class VideoController {
    @Autowired
    VideoService videoService;

    final String accessKey="Ff7teascb-5o79ihQAlXp2620YNEIna8rNuXFpSC";
    final String secreKey="ENjXgQiA-zMR4A5hEF-26roqjH8TPlAjq6L3YYbZ";
    final String bucket="wdehbcj-video";
    final String headlink="http://video.wdehbcj.top";

//    @PostMapping("save")
//    public Map<String,Object>

    @GetMapping("getvideolist")
    public Map<String ,Object> getVideoList(){
        List<Video> videoList=new ArrayList();
        videoList=videoService.findAll();
//        Map<Integer,String> idandimg=new HashMap<>();
//        for (Video video:videoList){
//            idandimg.put(video.getId(),video.getCoveraddress());
//        }
        return Json.success(videoList);
    }
    @GetMapping("getvideobyid")
    public Map<String,Object> getVideoById(@RequestParam String id){
        Video video=null;
        video =videoService.getOne(Integer.parseInt(id));
        return Json.success(video);
    }
    @GetMapping("getvideolistlike")
    public Map<String,Object> getVideoListLike(@RequestParam  String namelike){
        List<Video> videoList=new ArrayList();
        videoList=videoService.findByNameLike(namelike);
        return Json.success(videoList);
    }

}
