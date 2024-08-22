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
@Table(name = "card_news_images")
public class CardNewsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_news_id", referencedColumnName = "id")
    private CardNews cardNews;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "display_order")
    private int displayOrder;
}
