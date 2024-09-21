package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    /*
    * TODO
    *   - 사용자 아이디 username으로 검색
    * */

    Member findByUsername(String username);

}
