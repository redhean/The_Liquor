package net.theliquor.theliquor.test.controller;

import net.theliquor.theliquor.test.application.TestService;
import net.theliquor.theliquor.test.dto.TestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private final TestService testService;

    public HomeController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public String getDefault() {
        return "OK";
    }

    @PostMapping("/save")
    public ResponseEntity<TestDTO> saveTest(@RequestParam String name) {
        TestDTO testDTO = new TestDTO();
        System.out.println(name);
        testDTO.setName(name);
        TestDTO savedTestDTO = testService.test(testDTO);
        return ResponseEntity.ok(savedTestDTO);
    }

}
