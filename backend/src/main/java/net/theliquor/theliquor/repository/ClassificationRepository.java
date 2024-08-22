package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository <Classification, Integer> {

}
