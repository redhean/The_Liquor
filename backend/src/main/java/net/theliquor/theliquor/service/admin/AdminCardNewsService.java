package net.theliquor.theliquor.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.domain.CardNews;
import net.theliquor.theliquor.domain.CardNewsImage;
import net.theliquor.theliquor.domain.Classification;
import net.theliquor.theliquor.domain.Image;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.guide.AdminCardNewsRequestDTO;
import net.theliquor.theliquor.dto.guide.AdminCardNewsResponseDTO;
import net.theliquor.theliquor.repository.CardNewsImageRepository;
import net.theliquor.theliquor.repository.CardNewsRepository;
import net.theliquor.theliquor.repository.ClassificationRepository;
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
public class AdminCardNewsService {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    private final CardNewsRepository cardNewsRepository;
    private final CardNewsImageRepository cardNewsImageRepository;
    private final ClassificationRepository classificationRepository;

    public ResponseDTO saveCardNews(AdminCardNewsRequestDTO cardNewsRequest, List<MultipartFile> images) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // CardNews 객체 생성 및 유효성 검사
        CardNews cardNews = createOrUpdateCardNewsFromRequest(cardNewsRequest, null, errors);

        if(!errors.isEmpty()) {
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // CardNews 저장
            CardNews savedCardNews = cardNewsRepository.save(cardNews);

            // 이미지 업로드 처리
            savedCardNews.setFirstImagePath(handleImageUpload(images.getFirst(), savedCardNews, 1, errors));
            cardNewsRepository.save(savedCardNews);
            for(int i = 1; i < images.size(); i++) {
                handleImageUpload(images.get(i), savedCardNews, i + 1, errors);
            }

            // 이미지 처리에 오류가 있으면 예외를 발생시킴
            if (!errors.isEmpty()) {
                throw new RuntimeException("Image upload failed");
            }

            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            // 예외 발생 시 롤백 처리 및 에러 응답 설정

            // 저장된 이미지 삭제
            List<CardNewsImage> imagesToDelete = cardNewsImageRepository.findImagesByCardNewsId(cardNews.getId());
            for(CardNewsImage image : imagesToDelete) {
                File fileToDelete = new File(image.getImagePath());
                if (fileToDelete.exists()) {
                    if (!fileToDelete.delete()) {
                        errors.add(Responses.IMAGE_DELETE_FAILED);
                    }
                }
            }

            // 카드 뉴스 이미지 테이블 삭제
            cardNewsImageRepository.deleteAllByCardNewsId(cardNews.getId());

            // 카드 뉴스 삭제
            if(cardNews.getId() != null) {
                cardNewsRepository.deleteById(cardNews.getId());
            }
            errors.add(Responses.CARD_NEWS_SAVE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public AdminCardNewsResponseDTO findCardNewsById(Integer id) {
        AdminCardNewsResponseDTO result = new AdminCardNewsResponseDTO();
        Optional<CardNews> cardNews = cardNewsRepository.findById(id);

        if(cardNews.isPresent()) {
            result.setTitle(cardNews.get().getTitle());
            result.setImageCount(cardNews.get().getImageCount());

            // images
            List<CardNewsImage> images = cardNewsImageRepository.findImagesByCardNewsId(cardNews.get().getId());
            List<String> imagesPath = new ArrayList<>();
            for(CardNewsImage image : images) {
                imagesPath.add(image.getImagePath());
            }
            result.setImages(imagesPath);

            // classificationId
            List<Classification> classifications = classificationRepository.findClassificationChain(cardNews.get().getClassification().getId());
            result.setPrimaryClassificationId(classifications.getFirst().getId());
            if(classifications.size() > 1)
                result.setSecondaryClassificationId(classifications.get(1).getId());
        }

        return result;
    }

    public ResponseDTO updateCardNews(Integer id, AdminCardNewsRequestDTO cardNewsRequest, List<MultipartFile> images) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. 기존 CardNews 객체 조회
        CardNews existingCardNews = cardNewsRepository.findById(id).orElse(null);
        if (existingCardNews == null) {
            errors.add(Responses.CARD_NEWS_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        // 기존 삭제 -> 새로 업로드
        try {
            // 저장된 이미지 삭제
            List<CardNewsImage> imagesToDelete = cardNewsImageRepository.findImagesByCardNewsId(existingCardNews.getId());
            for(CardNewsImage image : imagesToDelete) {
                File fileToDelete = new File(image.getImagePath());
                if (fileToDelete.exists()) {
                    if (!fileToDelete.delete()) {
                        errors.add(Responses.IMAGE_DELETE_FAILED);
                    }
                }
            }

            // 카드 뉴스 이미지 테이블 삭제
            cardNewsImageRepository.deleteAllByCardNewsId(existingCardNews.getId());

            CardNews savedCardNews = createOrUpdateCardNewsFromRequest(cardNewsRequest, existingCardNews, errors);

            // 새로운 이미지 업로드 처리
            existingCardNews.setFirstImagePath(handleImageUpload(images.getFirst(), savedCardNews, 1, errors));
            cardNewsRepository.save(savedCardNews);
            for(int i = 1; i < images.size(); i++) {
                handleImageUpload(images.get(i), savedCardNews, i + 1, errors);
            }

            // 이미지 처리에 오류가 있으면 예외를 발생시킴
            if (!errors.isEmpty()) {
                throw new RuntimeException("Image upload failed");
            }

            cardNewsRepository.save(savedCardNews);
            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            errors.add(Responses.CARD_NEWS_UPDATE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    public ResponseDTO deleteCardNews(Integer id) {
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();

        // 1. 기존 CardNews 객체 조회
        CardNews existingCardNews = cardNewsRepository.findById(id).orElse(null);
        if (existingCardNews == null) {
            errors.add(Responses.CARD_NEWS_NOT_FOUND);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        try {
            // 2. 카드 뉴스에 연결된 이미지 목록 조회
            List<CardNewsImage> imagesToDelete = cardNewsImageRepository.findImagesByCardNewsId(existingCardNews.getId());

            // 3. 이미지 파일 삭제
            for (CardNewsImage image : imagesToDelete) {
                File fileToDelete = new File(image.getImagePath());
                if (fileToDelete.exists() && !fileToDelete.delete()) {
                    errors.add(Responses.IMAGE_DELETE_FAILED);
                }

                // 이미지 엔티티 삭제
                cardNewsImageRepository.delete(image);
            }

            // 4. 카드 뉴스 삭제
            cardNewsRepository.deleteById(existingCardNews.getId());

            result.setResult(Responses.Result.SUCCESS.ordinal());
        } catch (Exception e) {
            errors.add(Responses.CARD_NEWS_DELETE_FAILED);
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
        }

        return result;
    }

    private CardNews createOrUpdateCardNewsFromRequest(AdminCardNewsRequestDTO cardNewsRequest, CardNews cardNews, List<String> errors) {
        if(cardNews == null) {
            cardNews = new CardNews();
        }

        classificationRepository.findById(cardNewsRequest.getClassificationId())
                .ifPresentOrElse(cardNews::setClassification, () -> errors.add(Responses.CLASSIFICATION_NOT_FOUND));

        cardNews.setTitle(cardNewsRequest.getTitle());
        cardNews.setImageCount(cardNewsRequest.getImageCount());
        cardNews.setFirstImagePath("temp_path");

        return cardNews;
    }

    private String handleImageUpload(MultipartFile image, CardNews savedCardNews, Integer order, List<String> errors) {
        String imagePath = "";
        if (image != null && !image.isEmpty()) {
            if (image.getContentType().startsWith("image")) {
                try {
                    String imageFileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();

                    File directory = new File(uploadPath + "/card_news");
                    if (!directory.exists()) {
                        directory.mkdirs(); // 디렉토리가 없으면 생성
                    }

                    imagePath = uploadPath + "/card_news/" + imageFileName;

                    File file = new File(imagePath);
                    image.transferTo(file);

                    // 이미지 저장
                    CardNewsImage img = new CardNewsImage();
                    img.setCardNews(savedCardNews);
                    img.setImagePath(imagePath);
                    img.setDisplayOrder(order);
                    cardNewsImageRepository.save(img);

                } catch (IOException e) {
                    log.error("Failed to save image", e);
                    errors.add(order + "번째 " + Responses.IMAGE_UPLOAD_FAILED);
                }
            } else {
                log.error("Invalid image file type: " + image.getContentType());
                errors.add(order + "번째 " + Responses.INVALID_IMAGE_FILE);
            }
        } else {
            log.error("No image file provided");
            errors.add(order + "번째 " + Responses.EMPTY_IMAGE_FILE);
        }

        return imagePath;
    }
}
