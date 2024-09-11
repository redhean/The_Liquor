package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardNewsImageRepository extends JpaRepository<CardNewsImage, Long> {
    /*
    * TODO
    *   - CardNews에 따른 이미지 반환 (order by display_order) -> @Query
    *   - CardNews에 따른 이미지 전체 삭제 -> @Query
    *   - 새로운 CardNewsImage 추가 -> save
    *   - CardNewsImage 수정 -> save
    *   - CardNewsImage 삭제 -> delete
    * */

    @Query("SELECT cni FROM CardNewsImage cni WHERE cni.cardNews.id = :cardNewsId ORDER BY cni.displayOrder ASC")
    List<CardNewsImage> findImagesByCardNewsId(@Param("cardNewsId") Integer cardNewsId);

    @Modifying
    @Query("DELETE FROM CardNewsImage cni WHERE cni.cardNews.id = :cardNewsId")
    void deleteAllByCardNewsId(@Param("cardNewsId") Integer cardNewsId);

}
