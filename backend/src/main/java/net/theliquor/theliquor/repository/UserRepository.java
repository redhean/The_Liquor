package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*
    * TODO
    *   - 사용자 아이디 username으로 검색
    * */

    User findByUsername(String username);
}
