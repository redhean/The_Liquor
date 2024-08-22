package net.theliquor.theliquor.repository;

import net.theliquor.theliquor.domain.Liquor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorRepository extends JpaRepository<Liquor, Long> {

}
