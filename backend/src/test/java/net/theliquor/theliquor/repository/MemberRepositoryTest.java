package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Member;
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
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void UserFindByUsernameTest() {
        // Given
        Member user = new Member();
        user.setUsername("redhean");
        memberRepository.save(user);

        // When
        Member result = memberRepository.findByUsername("redhean");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("redhean");
    }

}
