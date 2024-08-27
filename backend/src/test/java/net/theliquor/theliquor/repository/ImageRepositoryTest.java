package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Image;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void FindImagePathByEntityTypeAndEntityId() {
        // Given
        for(int i = 0; i < 10; i++) {
            Image image = new Image();
            image.setEntityId(i + 1L);
            image.setEntityType(Image.EntityType.LIQUOR);
            image.setImagePath("path_liquor_" + (i + 1));
            imageRepository.save(image);
        }

        for(int i = 0; i < 10; i++) {
            Image image = new Image();
            image.setEntityId(i + 1L);
            image.setEntityType(Image.EntityType.BRAND);
            image.setImagePath("path_brand_" + (i + 1));
            imageRepository.save(image);
        }

        // When
        String resultLiquor = imageRepository.findImagePathByEntityTypeAndEntityId(Image.EntityType.LIQUOR, 1L);
        String resultBrand = imageRepository.findImagePathByEntityTypeAndEntityId(Image.EntityType.BRAND, 1L);

        // Then
        assertThat(resultLiquor).isEqualTo("path_liquor_1");
        assertThat(resultBrand).isEqualTo("path_brand_1");
    }
}
