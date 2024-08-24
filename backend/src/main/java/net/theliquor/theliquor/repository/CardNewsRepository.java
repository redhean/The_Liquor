package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNewsRepository extends JpaRepository<CardNews, Integer> {
    /*
    * TODO
    *   - 전체 CardNews 리스트 반환 -> findAll()
    *   - 검색어 필터링 반환 -> @Query
    *   - 새로운 CardNews 생성 -> save
    *   - CardNews 수정 -> save
    *   - CardNews 삭제 -> delete
    * */

}
