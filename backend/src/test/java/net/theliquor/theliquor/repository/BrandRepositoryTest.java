package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = "/data.sql")
@ActiveProfiles("test")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    void BrandFindByProducerIdTest() {
        // Given
        Integer producerId = producerRepository.findAll().getFirst().getId();

        // When
        List<Brand> brands = brandRepository.findByProducerId(producerId);

        // Then
        assertThat(brands)
                .isNotEmpty()
                .allSatisfy(brand -> {
                    assertThat(brand.getProducer().getId()).isEqualTo(producerId); // 브랜드의 프로듀서 ID가 주어진 ID와 같음
                });
    }

    @Test
    void BrandFindAllTest() {
        // Given

        // When
        List<Brand> brands = brandRepository.findAll();

        // Then
        assertThat(brands.size()).isEqualTo(18);
    }

    @Test
    void BrandDeleteByIdTest() {
        // Given
        Brand brand = brandRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("No brand found"));

        // When
        brandRepository.deleteById(brand.getId());

        // Then
        Optional<Brand> deletedBrand = brandRepository.findById(brand.getId());
        assertThat(deletedBrand).isNotPresent();

    }
}
