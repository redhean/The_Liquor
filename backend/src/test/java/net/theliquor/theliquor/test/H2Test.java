package net.theliquor.theliquor.test;

import net.theliquor.theliquor.test.application.TestService;
import net.theliquor.theliquor.test.dto.TestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class H2Test {

    @Autowired
    private TestService testService;

    @Autowired
    private DataSource dataSource;

    @Test
    @Transactional
    void SaveTest() {
        TestDTO testDTO = new TestDTO();
        testDTO.setName("hello");

        // 엔티티 저장
        TestDTO savedTestDTO = testService.test(testDTO);

        // 저장 후 검증 로직 추가
        Optional<TestDTO> retrievedDTO = testService.findById(savedTestDTO.getId());
        assertNotNull(retrievedDTO);
        retrievedDTO.ifPresent(dto -> assertEquals("hello", dto.getName()));
    }
}
