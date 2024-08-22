package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNewsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNewsImageRepository extends JpaRepository<CardNewsImage, Long> {

}
