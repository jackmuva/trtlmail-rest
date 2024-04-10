package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(UploadedImage image);
    DownloadedImage downloadImage(String filename);
    void deleteImage(String filename);
    void deleteImagesInEntry(Long entryId);
    String generateFileName(MultipartFile multiPart);
}
