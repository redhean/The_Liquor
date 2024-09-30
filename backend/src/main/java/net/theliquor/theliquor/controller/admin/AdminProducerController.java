package net.theliquor.theliquor.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.domain.Producer;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.service.admin.AdminProducerService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminProducerController {

    private final AdminProducerService adminProducerService;
    private final MessageSource messageSource;

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
        String name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            ResponseDTO response = new ResponseDTO();
            List<String> errors = new ArrayList<>();
            Locale locale = Locale.KOREA;

            errors.add(messageSource.getMessage("AdminProducerController.name.null", new Object[]{0}, locale));
            response.setResult(Responses.Result.FAIL.ordinal());
            response.setErrors(errors);
            return response;
        }

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
        // Validation
        String name = request.get("name");
        if (name == null || name.trim().isEmpty()) {
            ResponseDTO response = new ResponseDTO();
            List<String> errors = new ArrayList<>();
            Locale locale = Locale.KOREA;

            errors.add(messageSource.getMessage("AdminProducerController.name.null", new Object[]{0}, locale));
            response.setResult(Responses.Result.FAIL.ordinal());
            response.setErrors(errors);
            return response;
        }

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
