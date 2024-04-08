package com.jackmu.slowcapsules.model.editorjs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ImageLookup")
public class ImageLookup {
    @Id
    @Column(name = "image_filename")
    private String imageFilename;

    @Column(name = "entry_id")
    private Long entryId;
}
