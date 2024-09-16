package net.theliquor.theliquor.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.domain.Producer;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.repository.ProducerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminProducerService {

    private final ProducerRepository producerRepository;

    public List<Producer> findAllProducer() {
        List<Producer> result = producerRepository.findAll();
        return result;
    }

    public Optional<Producer> findProducerById(Integer id) {
        Optional<Producer> producer = producerRepository.findById(id);
        return producer;
    }

    public ResponseDTO saveProducer(String name) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 이름이 비어 있는지 체크
        if (name == null || name.trim().isEmpty()) {
            errors.add(Responses.PRODUCER_NAME_INVALID_VALUE);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        // Producer 객체 생성 및 저장
        Producer producer = new Producer();
        producer.setName(name);

        try {
            producerRepository.save(producer);
            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            errors.add(Responses.PRODUCER_SAVE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO updateProducer(Integer id, String newName) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 해당 id의 Producer가 존재하는지 확인
        Producer producer = producerRepository.findById(id).orElse(null);
        if (producer == null) {
            errors.add(Responses.PRODUCER_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        // 이름 업데이트
        if (newName == null || newName.trim().isEmpty()) {
            errors.add(Responses.PRODUCER_NAME_INVALID_VALUE);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        producer.setName(newName);

        try {
            producerRepository.save(producer); // 업데이트 후 저장
            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {;
            errors.add(Responses.PRODUCER_UPDATE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO deleteProducer(Integer id) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 해당 id의 Producer가 존재하는지 확인
        Producer producer = producerRepository.findById(id).orElse(null);
        if (producer == null) {
            errors.add(Responses.PRODUCER_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            producerRepository.deleteById(id); // Producer 삭제
            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            errors.add(Responses.PRODUCER_DELETE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }
}
