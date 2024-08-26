package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNews;
import net.theliquor.theliquor.domain.CardNewsImage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = "/data.sql")
@ActiveProfiles("test")
public class CardNewsImageRepositoryTest {

    @Autowired
    private CardNewsRepository cardNewsRepository;

    @Autowired
    private CardNewsImageRepository cardNewsImageRepository;

    @Test
    void findImagesByCardNewsIdTest() {
        // Given
        CardNews cardNews = cardNewsRepository.findAll().stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("No card news found"));

        for(int i = 0; i < 10; i++) {
            CardNewsImage cardNewsImage = new CardNewsImage();
            cardNewsImage.setCardNews(cardNews);
            cardNewsImage.setImagePath("path_" + (i + 1));
            cardNewsImage.setDisplayOrder(i + 1);
            cardNewsImageRepository.save(cardNewsImage);
        }

        // When
        List<CardNewsImage> result = cardNewsImageRepository.findImagesByCardNewsId(cardNews.getId());

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(10);

        for (int i = 0; i < result.size(); i++) {
            CardNewsImage image = result.get(i);
            assertThat(image.getCardNews()).isEqualTo(cardNews);
            assertThat(image.getImagePath()).isEqualTo("path_" + (i + 1));
            assertThat(image.getDisplayOrder()).isEqualTo(i + 1);
        }
    }
}
