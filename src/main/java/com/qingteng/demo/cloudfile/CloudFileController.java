package com.qingteng.demo.cloudfile;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * CloudFileController
 */
@Api(tags = {"文件上传"})
@RestController
@RequestMapping("/cloudFiles/")
public class CloudFileController {

    private CloudFileService cloudFileService;

    @Autowired
    CloudFileController(CloudFileService cloudFileService) {
        this.cloudFileService = cloudFileService;
    }

    @PostMapping("/upload")
    public HashMap<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.cloudFileService.uploadFile(file);
    }
}
