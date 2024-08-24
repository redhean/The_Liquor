package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository <Classification, Integer> {
    /*
    * TODO
    *   - 상위 주종을 타고 올라가 리스트로 반환 -> Querydsl
    * */


}
