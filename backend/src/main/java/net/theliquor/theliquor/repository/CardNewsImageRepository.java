package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNewsImageRepository extends JpaRepository<CardNewsImage, Long> {
    /*
    * TODO
    *   - CardNews에 따른 이미지 반환 (order by display_order) -> @Query
    *   - 새로운 CardNewsImage 추가 -> save
    *   - CardNewsImage 수정 -> save
    *   - CardNewsImage 삭제 -> delete
    * */
}
