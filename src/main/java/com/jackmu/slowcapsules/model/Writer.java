package com.jackmu.slowcapsules.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "pen_name")
    private String penName;

    @Column(name = "email")
    private String email;
}
