package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNews;
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
@ActiveProfiles("test")
public class CardNewsRepositoryTest {

    @Autowired
    private CardNewsRepository cardNewsRepository;

    @Autowired
    private ClassificationRepository classificationRepository;

    @Test
    void FindCardNewsByFiltersTest() {
        // Given
        for(int i = 1; i <= 20; i++) {
            CardNews cardNews = new CardNews();
            cardNews.setTitle("Test Card News" + i);
            cardNewsRepository.save(cardNews);
        }

        // When
        // 1,10,11,12,13,14,15,16,17,18,19
        List<CardNews> result = cardNewsRepository.findCardNewsByFilters("1", 0);

        // Then
        System.out.println(result.size());
        assertThat(result.size()).isEqualTo(9);     // max size = 9
        assertThat(result).allMatch(cardNews -> cardNews.getTitle().contains("1"));
    }

    @Test
    @Sql(scripts = "/data.sql")
    void FindCardNewsByClassificationTest() {
        // Given

        // When
        List<CardNews> result_whisky = cardNewsRepository.findCardNewsByClassification(3);     // 위스키
        List<CardNews> result_beer = cardNewsRepository.findCardNewsByClassification(2);     // 맥주

        // Then
        System.out.println(result_whisky.size());
        System.out.println(result_beer.size());
        assertThat(result_whisky.size()).isEqualTo(12);
        assertThat(result_beer.size()).isEqualTo(13);
    }
}
