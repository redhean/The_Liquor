package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Liquor;
import net.theliquor.theliquor.repository.impl.LiquorSearchCond;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = "/data.sql")
@ActiveProfiles("test")
public class LiquorRepositoryTest {
    @Autowired
    private LiquorRepository liquorRepository;

    @Test
    void LiquorFilterTest() {
        LiquorSearchCond cond = new LiquorSearchCond();

        cond.setTerm("wine");
        cond.setLiquorClass(1);
        cond.setAlcMin(1.0F);
        cond.setAlcMax(10.0F);
        cond.setAvail(Boolean.TRUE);
        cond.setPage(0);
        cond.setBrand(1L);

        List<Liquor> result = liquorRepository.findLiquorsByFilters(cond);

        // 결과 검증
        assertThat(result)
                .isNotEmpty() // 결과가 비어있지 않음
                .hasSizeGreaterThan(0) // 결과의 크기가 0보다 큼
                .allSatisfy(liquor -> {
                    assertThat(liquor.getEnglishName().toLowerCase()).contains("wine"); // 이름에 'wine'이 포함됨 (대소문자 구분 없음)
                    assertThat(liquor.getClassification().getId()).isEqualTo(1); // 주종이 1
                    assertThat(liquor.getAlcohol()).isBetween(1.0F, 10.0F); // 알코올 도수가 1.0과 10.0 사이
                    assertThat(liquor.getIsDomesticSale()).isTrue(); // 판매 여부가 true
                    assertThat(liquor.getBrand().getId()).isEqualTo(1L); // 브랜드가 1
                });
    }

    @Test
    void LiquorSaveAndFindAllTest() {
        List<Liquor> beforeResult = liquorRepository.findAll();

        Liquor liquor = new Liquor();
        liquorRepository.save(liquor);

        List<Liquor> afterResult = liquorRepository.findAll();

        int beforeSize = beforeResult.size();
        int afterSize = afterResult.size();
        System.out.println("beforeSize: " + beforeSize + " afterSize: " + afterSize);

        // 결과 검증
        assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

    @Test
    void LiquorFindByIdTest() {
        // 기존의 술을 하나 가져와서
        Liquor liquor = liquorRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("No liquor found"));

        // 그 술의 ID로 조회
        Optional<Liquor> foundLiquor = liquorRepository.findById(liquor.getId());

        // 결과 검증
        assertThat(foundLiquor)
                .isPresent()
                .hasValueSatisfying(l -> {
                    assertThat(l.getId()).isEqualTo(liquor.getId());
                    assertThat(l.getEnglishName()).isEqualTo(liquor.getEnglishName());
                });
    }

    @Test
    void LiquorSaveAndUpdateTest() {
        // 새로운 술 저장
        Liquor liquor = new Liquor();
        liquor.setEnglishName("New Liquor");
        liquor = liquorRepository.save(liquor);

        // 저장된 술 수정
        liquor.setEnglishName("Updated Liquor");
        Liquor updatedLiquor = liquorRepository.save(liquor);

        // 결과 검증
        assertThat(liquor.getId()).isEqualTo(updatedLiquor.getId());
        assertThat(updatedLiquor.getEnglishName()).isEqualTo("Updated Liquor");
    }

    @Test
    void LiquorDeleteByIdTest() {
        // 기존의 술을 하나 가져와서
        Liquor liquor = liquorRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("No liquor found"));

        // 술 삭제
        liquorRepository.deleteById(liquor.getId());

        // 삭제된 술을 조회
        Optional<Liquor> deletedLiquor = liquorRepository.findById(liquor.getId());

        // 결과 검증
        assertThat(deletedLiquor).isNotPresent();
    }
}
