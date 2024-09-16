package net.theliquor.theliquor.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.liquor.LiquorRequestDTO;
import net.theliquor.theliquor.dto.liquor.LiquorResponseDTO;
import net.theliquor.theliquor.service.admin.AdminLiquorService;
import net.theliquor.theliquor.service.user.LiquorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminLiquorController {

    private final AdminLiquorService adminLiquorService;
    private final LiquorService liquorService;

    // 술 생성
    @PostMapping(value = "post/liquor", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO createLiquor(
            @RequestPart(value = "liquor") LiquorRequestDTO liquor,
            @RequestPart(value = "image") MultipartFile image
    ) {
        // Validation
        // TODO

        // MultipartFile이 모두 image인지 확인
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        if(!image.getContentType().startsWith("image")) {
            errors.add(Responses.INVALID_IMAGE_FILE);
        }

        if(!errors.isEmpty()){
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        result = adminLiquorService.saveLiquor(liquor, image);
        return result;
    }

    // 술 수정
    @GetMapping("update/liquor/{id}")
    public LiquorResponseDTO getLiquor(@PathVariable Long id) {
        LiquorResponseDTO result = liquorService.findLiquorById(id);
        return result;
    }

    @PutMapping(value = "update/liquor/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO updateLiquor(
            @PathVariable Long id,
            @RequestPart(value = "liquor") LiquorRequestDTO liquor,
            @RequestPart(value = "image") MultipartFile image
    ) {

        // Validation
        // TODO

        // MultipartFile이 모두 image인지 확인
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        if(!image.getContentType().startsWith("image")) {
            errors.add(Responses.INVALID_IMAGE_FILE);
        }

        if(!errors.isEmpty()){
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        result = adminLiquorService.updateLiquor(liquor, image, id);
        return result;
    }

    // 술 삭제
    @DeleteMapping("delete/liquor/{id}")
    public ResponseDTO deleteLiquor(@PathVariable Long id) {
        ResponseDTO result = adminLiquorService.deleteLiquor(id);
        return result;
    }
}
