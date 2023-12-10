package com.jackmu.slowcapsules.service;

import com.amazonaws.services.s3.transfer.Download;
import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;

public interface ImageService {
    void uploadImage(UploadedImage image);
    DownloadedImage downloadImage(String filename);
}
