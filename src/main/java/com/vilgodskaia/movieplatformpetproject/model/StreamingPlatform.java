package com.vilgodskaia.movieplatformpetproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "streaming_platform")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class StreamingPlatform {

    /**
     * Id
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    /**
     * Name
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
