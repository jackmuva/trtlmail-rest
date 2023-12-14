package com.jackmu.slowcapsules.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Service
@Profile("!local")
public class S3ImageService implements ImageService{
    @Autowired
    AmazonS3 amazonS3;
    private static final Logger LOGGER = LoggerFactory.getLogger(S3ImageService.class);
    private static final String S3_BUCKET_NAME = "trtlmail-images";
    private static final String S3_DOWNLOAD_URL = "https://trtlmail-images.s3.amazonaws.com/";

    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convFile;
    }

    @Override
    public String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    @Override
    public String uploadImage(UploadedImage image) {
        File imageFile = convertMultiPartToFile(image.getImage());
        String filename = generateFileName(image.getImage());
        try{
            PutObjectRequest putObjectRequest = new PutObjectRequest(S3_BUCKET_NAME, filename, imageFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            return filename;
        } catch(Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public DownloadedImage downloadImage(String filename) {
        DownloadedImage downloadedImage = new DownloadedImage(1, Collections.singletonMap("url",
                S3_DOWNLOAD_URL + filename));
        return downloadedImage;
    }
}
