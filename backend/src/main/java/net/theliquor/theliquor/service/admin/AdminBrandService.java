package net.theliquor.theliquor.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.domain.Brand;
import net.theliquor.theliquor.domain.Image;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.brand.BrandDTO;
import net.theliquor.theliquor.dto.brand.BrandInFilterDTO;
import net.theliquor.theliquor.dto.brand.BrandRequestDTO;
import net.theliquor.theliquor.repository.BrandRepository;
import net.theliquor.theliquor.repository.ClassificationRepository;
import net.theliquor.theliquor.repository.ImageRepository;
import net.theliquor.theliquor.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminBrandService {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    private final ProducerRepository producerRepository;
    private final ClassificationRepository classificationRepository;

    public List<BrandInFilterDTO> findBrandsByProducer(Integer id) {
        List<Brand> brands = brandRepository.findByProducerId(id);
        List<BrandInFilterDTO> result = new ArrayList<>();

        for(Brand brand : brands) {
            BrandInFilterDTO newBrand = new BrandInFilterDTO();
            newBrand.setId(brand.getId());
            newBrand.setName(brand.getName());
            result.add(newBrand);
        }

        return result;
    }

    public BrandDTO findBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        BrandDTO result = new BrandDTO();

        if (brand.isPresent()) {
            result.setId(brand.get().getId());
            result.setClassificationId(brand.get().getClassification() != null ? brand.get().getClassification().getId() : null);
            result.setClassificationName(brand.get().getClassification() != null ? brand.get().getClassification().getName() : null);
            result.setName(brand.get().getName());
            result.setImagePath(imageRepository.findImagePathByEntityTypeAndEntityId(Image.EntityType.BRAND, brand.get().getId()));
            result.setColor(brand.get().getColor());
        }

        return result;
    }

    public ResponseDTO saveBrand(BrandRequestDTO brandRequest, MultipartFile image) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // Brand 객체 생성 및 유효성 검사
        Brand brand = createOrUpdateBrandFromRequest(brandRequest, null, errors);
        log.info("brand id: {}", brand.getId());

        if (!errors.isEmpty()) {
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // Brand 객체 저장
            brandRepository.save(brand);

            // 이미지 업로드 처리
            handleImageUpload(image, brand, errors);

            // 이미지 처리에 오류가 있으면 예외를 발생시킴
            if (!errors.isEmpty()) {
                throw new RuntimeException("Image upload failed");
            }

            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 예외 발생 시 롤백 처리 및 에러 응답 설정
            if (brand.getId() != null) {
                brandRepository.deleteById(brand.getId()); // 수동으로 Brand 삭제
            }
            errors.add(Responses.BRAND_SAVE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO updateBrand(Long id, BrandRequestDTO brandRequest, MultipartFile image) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. 기존 Brand 객체 조회
        Brand existingBrand = brandRepository.findById(id).orElse(null);
        if(existingBrand == null) {
            errors.add(Responses.BRAND_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // 2. Brand 객체 업데이트
            Brand updatedBrand = createOrUpdateBrandFromRequest(brandRequest, existingBrand, errors);

            // 3. 이미지 처리
            if (image != null && !image.isEmpty()) {
                Image existingImage = imageRepository.findImageByEntityTypeAndEntityId(Image.EntityType.BRAND, updatedBrand.getId());

                if (existingImage != null) {
                    // 3.1. 기존 이미지 파일 삭제
                    File fileToDelete = new File(existingImage.getImagePath());
                    if (fileToDelete.exists()) {
                        if (!fileToDelete.delete()) {
                            errors.add(Responses.IMAGE_DELETE_FAILED);
                        }
                    }
                    // 3.2. 기존 이미지 엔티티 삭제
                    imageRepository.deleteById(existingImage.getId());
                }

                // 3.3. 새 이미지 업로드 처리
                handleImageUpload(image, updatedBrand, errors);

                // 이미지 처리에 오류가 있으면 예외를 발생시킴
                if (!errors.isEmpty()) {
                    throw new RuntimeException("Image upload failed");
                }
            }

            // 4. Liquor 객체 저장
            brandRepository.save(updatedBrand);

            // 5. 결과 설정
            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 6. 예외 발생 시 롤백 처리 및 에러 응답 설정
            errors.add(Responses.BRAND_UPDATE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO deleteBrand(Long id) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. Brand 객체 조회
        Brand existingBrand = brandRepository.findById(id).orElse(null);
        if (existingBrand == null) {
            errors.add(Responses.BRAND_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // 2. 관련 이미지 조회 및 삭제
            Image existingImage = imageRepository.findImageByEntityTypeAndEntityId(Image.EntityType.BRAND, id);
            if (existingImage != null) {
                // 2.1. 이미지 파일 삭제
                File fileToDelete = new File(existingImage.getImagePath());
                if (fileToDelete.exists()) {
                    if (!fileToDelete.delete()) {
                        errors.add(Responses.IMAGE_DELETE_FAILED);
                    }
                }

                // 이미지 처리에 오류가 있으면 예외를 발생시킴
                if (!errors.isEmpty()) {
                    throw new RuntimeException("Image upload failed");
                }

                // 2.2. 이미지 엔티티 삭제
                imageRepository.deleteById(existingImage.getId());
            }

            // 3. Liquor 객체 삭제
            brandRepository.deleteById(id);

            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 4. 예외 처리
            errors.add(Responses.BRAND_DELETE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    private Brand createOrUpdateBrandFromRequest(BrandRequestDTO brandRequest, Brand brand, List<String> errors) {
        if(brand == null) {
            brand = new Brand();
        }

        producerRepository.findById(brandRequest.getProducerId())
                .ifPresentOrElse(brand::setProducer, () -> errors.add(Responses.PRODUCER_NOT_FOUND));

        classificationRepository.findById(brandRequest.getClassificationId())
                .ifPresentOrElse(brand::setClassification, () -> errors.add(Responses.CLASSIFICATION_NOT_FOUND));

        brand.setName(brandRequest.getName());
        brand.setColor(brandRequest.getColor());

        return brand;
    }

    private void handleImageUpload(MultipartFile image, Brand savedBrand, List<String> errors) {
        if (image != null && !image.isEmpty()) {
            if (image.getContentType().startsWith("image")) {
                try {
                    String imageFileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();

                    File directory = new File(uploadPath + "/brand");
                    if (!directory.exists()) {
                        directory.mkdirs(); // 디렉토리가 없으면 생성
                    }

                    String imagePath = uploadPath + "/brand/" + imageFileName;

                    File file = new File(imagePath);
                    image.transferTo(file);

                    // 이미지 저장
                    Image img = new Image();
                    img.setEntityType(Image.EntityType.LIQUOR);
                    img.setEntityId(savedBrand.getId());
                    img.setImagePath(imagePath);
                    imageRepository.save(img);

                } catch (IOException e) {
                    log.error("Failed to save image", e);
                    errors.add(Responses.IMAGE_UPLOAD_FAILED);
                }
            } else {
                log.error("Invalid image file type: " + image.getContentType());
                errors.add(Responses.INVALID_IMAGE_FILE);
            }
        } else {
            log.error("No image file provided");
            errors.add(Responses.EMPTY_IMAGE_FILE);
        }
    }
}
