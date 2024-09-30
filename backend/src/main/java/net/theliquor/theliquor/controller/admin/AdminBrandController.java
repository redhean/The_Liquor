package net.theliquor.theliquor.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.brand.BrandDTO;
import net.theliquor.theliquor.dto.brand.BrandInFilterDTO;
import net.theliquor.theliquor.dto.brand.BrandRequestDTO;
import net.theliquor.theliquor.service.admin.AdminBrandService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Validated
public class AdminBrandController {

    private final AdminBrandService adminBrandService;

    // 브랜드 리스트 반환
    @GetMapping("brand")
    public List<BrandInFilterDTO> getBrandsByProducer(@RequestParam(value = "producer") Integer producerId) {
        List<BrandInFilterDTO> result = adminBrandService.findBrandsByProducer(producerId);
        return result;
    }

    // 브랜드 생성
    @PostMapping(value = "post/brand", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO createBrand(
            @RequestPart(value = "brand") @Valid BrandRequestDTO brand,
            @RequestPart(value = "image") MultipartFile image
            ) {

        // Validation
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

        result = adminBrandService.saveBrand(brand, image);
        return result;
    }

    // 브랜드 수정
    @GetMapping("update/brand/{id}")
    public BrandDTO getBrand(@PathVariable Long id) {
        BrandDTO result = adminBrandService.findBrandById(id);
        return result;
    }

    @PutMapping("update/brand/{id}")
    public ResponseDTO updateBrand(
            @PathVariable Long id,
            @RequestPart(value = "brand") @Valid BrandRequestDTO brand,
            @RequestPart(value = "image") MultipartFile image
    ) {
        // Validation
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

        result = adminBrandService.updateBrand(id, brand, image);
        return result;
    }


    // 브랜드 삭제
    @DeleteMapping("delete/brand/{id}")
    public ResponseDTO deleteBrand(@PathVariable Long id) {
        ResponseDTO result = adminBrandService.deleteBrand(id);
        return result;
    }

}
