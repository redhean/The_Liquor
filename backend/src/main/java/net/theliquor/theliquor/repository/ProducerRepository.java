package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {
    /*
    * TODO
    *   - 새로운 생산자 저장 -> save
    *   - 생산자 수정 -> save
    *   - 생산자 삭제 -> deleteById(id)
    *   - 생산자 전체 리스트 반환 -> findAll()
    * */

    @Query("SELECT p FROM Producer p WHERE p.name LIKE %:term%")
    Page<Producer> findProducerByTerm(@Param("term") String term, Pageable pageable);
}
