package net.theliquor.theliquor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_news")
public class CardNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "classification_id", referencedColumnName = "id")
    private Classification classification;

    @Column(name = "title")
    private String title;

    @Column(name = "first_image_path")
    private String firstImagePath;

    @Column(name = "image_count")
    private int imageCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")); // 엔티티가 처음 생성될 때 현재 시간 설정
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")); // 엔티티가 처음 생성될 때 현재 시간 설정
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")); // 엔티티가 업데이트될 때 현재 시간으로 갱신
    }
}
