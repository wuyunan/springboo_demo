package com.qingteng.demo.respository.impl;

import com.qingteng.demo.respository.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImplA implements VideoService {

    @Override
    public String getVideoName() {
        return "三生三世十里桃花";
    }

}
