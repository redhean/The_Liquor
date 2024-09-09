package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Liquor;
import net.theliquor.theliquor.repository.impl.LiquorSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface LiquorRepository extends JpaRepository<Liquor, Long>, LiquorSearch {
    /*
    * TODO
    *   - 검색어, 주종, 최저도수, 최고도수, 국내판매여부, 브랜드, 페이지를 인자로 필터링 -> Querydsl
    *   - 술 전체 리스트 반환 -> findAll()
    *   - id를 기준으로 주종 상세 불러오기 -> findById(id)
    *   - 새로운 술 저장 -> save
    *   - 술 수정 -> save
    *   - 술 삭제 -> deleteById(id)
    * */

    @Query("SELECT l FROM Liquor l WHERE l.koreanName LIKE %:term% OR l.englishName LIKE %:term%")
    Page<Liquor> findLiquorByTerm(@Param("term") String term, Pageable pageable);


}
