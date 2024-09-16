package net.theliquor.theliquor.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.domain.*;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.liquor.LiquorRequestDTO;
import net.theliquor.theliquor.dto.liquor.LiquorResponseDTO;
import net.theliquor.theliquor.repository.*;
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
public class AdminLiquorService {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    private final LiquorRepository liquorRepository;
    private final ImageRepository imageRepository;
    private final ProducerRepository producerRepository;
    private final BrandRepository brandRepository;
    private final ClassificationRepository classificationRepository;

    public ResponseDTO saveLiquor(LiquorRequestDTO liquorRequest, MultipartFile image) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // Liquor 객체 생성 및 유효성 검사
        Liquor liquor = createOrUpdateLiquorFromRequest(liquorRequest, null, errors);

        if (!errors.isEmpty()) {
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // Liquor 객체 저장
            liquorRepository.save(liquor);

            // 이미지 업로드 처리
            handleImageUpload(image, liquor, errors);

            // 이미지 처리에 오류가 있으면 예외를 발생시킴
            if (!errors.isEmpty()) {
                throw new RuntimeException("Image upload failed");
            }

            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 예외 발생 시 롤백 처리 및 에러 응답 설정
            if (liquor.getId() != null) {
                brandRepository.deleteById(liquor.getId()); // 수동으로 Liquor 삭제
            }
            errors.add(Responses.LIQUOR_SAVE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO updateLiquor(LiquorRequestDTO liquorRequest, MultipartFile image, Long id) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. 기존 Liquor 객체 조회
        Liquor existingLiquor = liquorRepository.findById(id).orElse(null);
        if (existingLiquor == null) {
            errors.add(Responses.LIQUOR_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // 2. Liquor 객체 업데이트
            Liquor updatedLiquor = createOrUpdateLiquorFromRequest(liquorRequest, existingLiquor, errors);

            // 3. 이미지 처리
            if (image != null && !image.isEmpty()) {
                Image existingImage = imageRepository.findImageByEntityTypeAndEntityId(Image.EntityType.LIQUOR, updatedLiquor.getId());

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
                handleImageUpload(image, updatedLiquor, errors);

                // 이미지 처리에 오류가 있으면 예외를 발생시킴
                if (!errors.isEmpty()) {
                    throw new RuntimeException("Image upload failed");
                }
            }

            // 4. Liquor 객체 저장
            liquorRepository.save(updatedLiquor);

            // 5. 결과 설정
            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 6. 예외 발생 시 롤백 처리 및 에러 응답 설정
            errors.add(Responses.LIQUOR_UPDATE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO deleteLiquor(Long id) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. Liquor 객체 조회
        Liquor existingLiquor = liquorRepository.findById(id).orElse(null);
        if (existingLiquor == null) {
            errors.add(Responses.LIQUOR_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // 2. 관련 이미지 조회 및 삭제
            Image existingImage = imageRepository.findImageByEntityTypeAndEntityId(Image.EntityType.LIQUOR, id);
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
            liquorRepository.deleteById(id);

            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 4. 예외 처리
            errors.add(Responses.LIQUOR_DELETE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    private Liquor createOrUpdateLiquorFromRequest(LiquorRequestDTO liquorRequest, Liquor liquor, List<String> errors) {
        if (liquor == null) {
            liquor = new Liquor();  // 신규 Liquor 객체 생성
        }

        producerRepository.findById(liquorRequest.getProducerId())
                .ifPresentOrElse(liquor::setProducer, () -> errors.add(Responses.PRODUCER_NOT_FOUND));

        brandRepository.findById(liquorRequest.getBrandId())
                .ifPresentOrElse(liquor::setBrand, () -> errors.add(Responses.BRAND_NOT_FOUND));

        classificationRepository.findById(liquorRequest.getClassificationId())
                .ifPresentOrElse(liquor::setClassification, () -> errors.add(Responses.CLASSIFICATION_NOT_FOUND));

        liquor.setKoreanName(liquorRequest.getKoreanName());
        liquor.setEnglishName(liquorRequest.getEnglishName());
        liquor.setCountry(liquorRequest.getCountry());
        liquor.setAlcohol(liquorRequest.getAlcohol());
        liquor.setAged(liquorRequest.getAged());
        liquor.setPrice(liquorRequest.getPrice());
        liquor.setIbu(liquorRequest.getIbu());
        liquor.setIsDomesticSale(liquorRequest.getIsDomesticSale());
        liquor.setDescription(liquorRequest.getDescription());

        return liquor;
    }

    private void handleImageUpload(MultipartFile image, Liquor savedLiquor, List<String> errors) {
        if (image != null && !image.isEmpty()) {
            if (image.getContentType().startsWith("image")) {
                try {
                    String imageFileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();

                    File directory = new File(uploadPath + "/liquor");
                    if (!directory.exists()) {
                        directory.mkdirs(); // 디렉토리가 없으면 생성
                    }

                    String imagePath = uploadPath + "/liquor/" + imageFileName;

                    File file = new File(imagePath);
                    image.transferTo(file);

                    // 이미지 저장
                    Image img = new Image();
                    img.setEntityType(Image.EntityType.LIQUOR);
                    img.setEntityId(savedLiquor.getId());
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

