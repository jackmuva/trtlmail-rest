package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.controller.EditorJsController;
import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
@Profile("local")
public class LocalImageService implements ImageService{
    private static final String S3UPLOADURL = "C://Users/jackm/Documents/trtlmail/s3/";
    private static final String S3DOWNLOADURL = "http://127.0.0.1:5000/";
    private static final Logger LOGGER = LoggerFactory.getLogger(EditorJsController.class);
    @Override
    public void uploadImage(UploadedImage image) {
        LOGGER.info("I'm here");
        try {
            image.getImage().transferTo(new File(S3UPLOADURL + image.getImage().getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DownloadedImage downloadImage(String filename) {
        LOGGER.info("i'm here");
        DownloadedImage downloadedImage = new DownloadedImage(1, Collections.singletonMap("url",
                S3DOWNLOADURL + filename));
        return downloadedImage;
    }
}
