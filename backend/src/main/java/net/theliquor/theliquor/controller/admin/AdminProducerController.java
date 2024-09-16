package net.theliquor.theliquor.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Producer;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.service.admin.AdminProducerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminProducerController {

    private final AdminProducerService adminProducerService;

    // 생산자 리스트 반환
    @GetMapping("producer")
    public List<Producer> getAllProducer() {
        List<Producer> result = adminProducerService.findAllProducer();
        return result;
    }

    // 생산자 생성
    @PostMapping("post/producer")
    public ResponseDTO createProducer(@RequestBody Map<String, String> request) {
        // Validate
        // TODO

        String name = request.get("name");
        ResponseDTO result = adminProducerService.saveProducer(name);
        return result;
    }

    // 생산자 수정
    @GetMapping("update/producer/{id}")
    public Optional<Producer> getProducer(@PathVariable Integer id) {
        Optional<Producer> result = adminProducerService.findProducerById(id);
        return result;
    }

    @PutMapping("update/producer/{id}")
    public ResponseDTO updateProducer(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request
    ) {
        String name = request.get("name");
        ResponseDTO result = adminProducerService.updateProducer(id, name);
        return result;
    }

    // 생산자 삭제
    @DeleteMapping("delete/producer/{id}")
    public ResponseDTO deleteProducer(@PathVariable Integer id) {
        ResponseDTO result = adminProducerService.deleteProducer(id);
        return result;
    }
}
