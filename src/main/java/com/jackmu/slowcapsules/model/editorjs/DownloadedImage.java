package com.jackmu.slowcapsules.model.editorjs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadedImage {
    private int success;
    private Map<String, String> file;
}