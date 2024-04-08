package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.controller.EditorJsController;
import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.ImageLookup;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import com.jackmu.slowcapsules.repository.ImageLookupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Service
@Profile("local-profile")
public class LocalImageService implements ImageService{
    @Autowired
    ImageLookupRepository imageLookupRepository;
    private static final String S3UPLOADURL = "C://Users/jackm/Documents/trtlpost/s3/";
    private static final String S3DOWNLOADURL = "http://127.0.0.1:8081/";
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalImageService.class);
    @Override
    public String uploadImage(UploadedImage image) {
        String filename = generateFileName(image.getImage());
        try {
            image.getImage().transferTo(new File(S3UPLOADURL + filename));
            mapImage(filename, image.getEntryId());
            return filename;
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public DownloadedImage downloadImage(String filename) {
        DownloadedImage downloadedImage = new DownloadedImage(1, Collections.singletonMap("url",
                S3DOWNLOADURL + filename));
        return downloadedImage;
    }

    @Override
    public void deleteImage(String filename) {

    }

    public void mapImage(String filename, Long entryId){
        ImageLookup image = new ImageLookup(filename, entryId);
        imageLookupRepository.save(image);
    }

    @Override
    public String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
}
