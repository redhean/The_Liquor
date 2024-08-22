package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.CardNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardNewsRepository extends JpaRepository<CardNews, Integer> {
}
