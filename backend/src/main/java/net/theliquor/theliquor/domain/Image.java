package net.theliquor.theliquor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    public enum EntityType {
        LIQUOR,    // 0
        BRAND      // 1
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "entity_type")
    private EntityType entityType;

    @Column(name = "image_path")
    private String imagePath;
}
