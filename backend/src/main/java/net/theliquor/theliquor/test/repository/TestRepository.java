package net.theliquor.theliquor.test.repository;

import net.theliquor.theliquor.test.dto.TestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestDTO, Long> {

    Optional<TestDTO> findById(Long id);
}
