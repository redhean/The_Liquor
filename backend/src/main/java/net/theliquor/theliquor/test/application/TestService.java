package net.theliquor.theliquor.test.application;

import net.theliquor.theliquor.test.dto.TestDTO;
import net.theliquor.theliquor.test.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public TestDTO test(TestDTO testDTO) {
        return testRepository.save(testDTO);
    }

    public Optional<TestDTO> findById(Long id) {
        return testRepository.findById(id);
    }
}
