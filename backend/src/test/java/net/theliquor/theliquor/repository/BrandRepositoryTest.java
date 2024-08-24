package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Brand;
import net.theliquor.theliquor.domain.Liquor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("BeforeEach");
        try (Connection conn = dataSource.getConnection()) {
            // 데이터베이스를 초기 상태로 재설정
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("data.sql"));
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("AfterEach");
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            // 데이터베이스의 참조 무결성 문제를 피하기 위해 순서대로 삭제
            stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");
            stmt.execute("TRUNCATE TABLE card_news_images");
            stmt.execute("TRUNCATE TABLE card_news");
            stmt.execute("TRUNCATE TABLE images");
            stmt.execute("TRUNCATE TABLE liquor");
            stmt.execute("TRUNCATE TABLE brand");
            stmt.execute("TRUNCATE TABLE producer");
            stmt.execute("TRUNCATE TABLE classifications");
            stmt.execute("TRUNCATE TABLE users");
            stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");
        }
    }

    @Test
    void BrandFindByProducerIdTest() {

    }

}
