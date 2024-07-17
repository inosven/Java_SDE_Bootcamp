package org.example.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    private String clientDefaultRegion = "us-east-1";
//    private String bucketName = "test-bucket";
    private AmazonS3 s3Client;
    @Autowired
    AmazonS3 amazonS3;
    public String uploadFile(MultipartFile file, String bucketName) {
        if  (file ==null) {
            logger.error("File not exists or is not a file");
            return "";
        }

        String uuid = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String newFileName = FilenameUtils.removeExtension(originalFileName) + uuid + "." + FilenameUtils.getExtension(originalFileName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        try{
            PutObjectRequest objectRequest = new PutObjectRequest(bucketName, file.getName(), file.getInputStream(), objectMetadata);
            amazonS3.putObject(objectRequest);
            logger.info("upload file {} to bucket {}",newFileName, bucketName);

        } catch (SdkClientException e){
            logger.error("failed to upload file {} to bucket {}",newFileName, bucketName);
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getUrl(bucketName, newFileName);
    }

    private String getUrl(String bucketName, String s3Key) {
        return amazonS3.getUrl(bucketName, s3Key).toString();
    }
}
