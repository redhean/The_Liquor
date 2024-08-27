package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    /*
    * TODO
    *   - entity_type과 entity_id를 입력받아 image_path 반환 -> @Query
    *   - 새로운 이미지 추가 -> save
    *   - 이미지 수정 -> save
    *   - 이미지 삭제 -> deleteById(id)
    * */

    @Query("SELECT i.imagePath FROM Image i WHERE i.entityType = :entityType AND i.entityId = :entityId")
    String findImagePathByEntityTypeAndEntityId(@Param("entityType") Image.EntityType entityType,
                                                @Param("entityId") Long entityId);
}
