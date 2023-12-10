package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!local")
public class S3ImageService implements ImageService{

    @Override
    public void uploadImage(UploadedImage image) {

    }

    @Override
    public DownloadedImage downloadImage(String filename) {
        return null;
    }
}
