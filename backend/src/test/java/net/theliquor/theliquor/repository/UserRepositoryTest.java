package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = "/data.sql")
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void UserFindByUsernameTest() {
        // Given
        User user = new User();
        user.setUsername("redhean");
        userRepository.save(user);

        // When
        User result = userRepository.findByUsername("redhean");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("redhean");
    }

}
