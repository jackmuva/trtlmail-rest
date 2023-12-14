package com.jackmu.slowcapsules.service;

import com.amazonaws.services.s3.transfer.Download;
import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(UploadedImage image);
    DownloadedImage downloadImage(String filename);

    String generateFileName(MultipartFile multiPart);
}
