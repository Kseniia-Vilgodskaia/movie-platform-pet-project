package com.vilgodskaia.movieplatform.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "streaming_platform")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
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
