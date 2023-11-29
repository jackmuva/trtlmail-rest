package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.editorjs.DownloadedImage;
import com.jackmu.slowcapsules.model.editorjs.UploadedImage;
import com.jackmu.slowcapsules.model.editorjs.UrlLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Collections;

@RestController
@RequestMapping("api")
public class EditorJsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditorJsController.class);
    private static final String S3UPLOADURL = "C://Users/jackm/Documents/slowcapsules/s3/";
    private static final String S3DOWNLOADURL = "http://127.0.0.1:8081/";

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/image/save", consumes = { "multipart/form-data" })
    public ResponseEntity<DownloadedImage> postImage(@ModelAttribute UploadedImage image) throws Exception{
        image.getImage().transferTo(new File(S3UPLOADURL + image.getImage().getOriginalFilename()));

        DownloadedImage downloadedImage = new DownloadedImage(1, Collections.singletonMap("url",
                        S3DOWNLOADURL + image.getImage().getOriginalFilename()));
        return new ResponseEntity<>(downloadedImage, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/fetchUrl")
    public ResponseEntity<UrlLink> getUrl(@RequestParam String url){
        UrlLink urlLink = new UrlLink(1, Collections.singletonMap("url", url));
        return new ResponseEntity<>(urlLink, HttpStatus.OK);
    }

    //TODO: scheduled service that deletes unused images
}
