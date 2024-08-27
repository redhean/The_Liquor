package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Classification;
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
public class ClassificationRepositoryTest {

    @Autowired
    private ClassificationRepository classificationRepository;

    @Test
    void FindClassificationChainTest() {
        // Given
        Integer portWine = 67;  // 와인 -> 주정강화 와인 -> 포트 와인

        // When
        List<Classification> result = classificationRepository.findClassificationChain(portWine);

        // Then
        assertThat(result).isNotNull();

        assertThat(result).hasSize(3);

        assertThat(result.get(0).getName()).isEqualTo("와인");
        assertThat(result.get(1).getName()).isEqualTo("주정강화 와인");
        assertThat(result.get(2).getName()).isEqualTo("포트 와인");

        for(Classification temp : result) {
            System.out.print(temp.getName() + " ");
        }
        System.out.println();
    }
}
