package org.example.controller;

import org.example.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FileService fileService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("uploading file {}", file.getOriginalFilename());
        String url = fileService.uploadFile(file, "car-service-dev1");
        logger.info("successfully uploaded file {} to s3 ", file.getOriginalFilename());
        return url;
    }
}
