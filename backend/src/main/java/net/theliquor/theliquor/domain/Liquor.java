package net.theliquor.theliquor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "liquor")
public class Liquor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "classification_id", referencedColumnName = "id")
    private Classification classification;

    @Column(name = "korean_name")
    private String koreanName;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "country")
    private String country;

    @Column(name = "alcohol")
    private float alcohol;

    @Column(name = "aged")
    private Integer aged;

    @Column(name = "price")
    private String price;

    @Column(name = "ibu")
    private Integer ibu;

    @Column(name = "is_domestic_sale")
    private Boolean isDomesticSale;

    @Column(name = "description")
    private String description;

    @Column(name = "adv")
    private Integer adv;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
