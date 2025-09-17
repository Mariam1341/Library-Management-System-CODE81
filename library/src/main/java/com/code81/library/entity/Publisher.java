package com.code81.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private Integer foundedYear;

    @Column(columnDefinition = "TEXT")
    private String bio;
}
