package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    /*
    * TODO
    *   - 브랜드 전체 리스트 반환 -> findAll()
    *   - 생산자에 따른 브랜드 리스트 반환
    *   - classification(1차 주종)에 따른 브랜드 리스트 반환
    *   - 새로운 브랜드 추가 -> save
    *   - 브랜드 수정 -> save
    *   - 브랜드 삭제 -> deleteById(id)
    * */

    List<Brand> findByProducerId(Integer producerId);
    List<Brand> findByClassificationId(Integer classificationId);
}
