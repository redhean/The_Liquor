package net.theliquor.theliquor.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.guide.AdminCardNewsRequestDTO;
import net.theliquor.theliquor.dto.guide.AdminCardNewsResponseDTO;
import net.theliquor.theliquor.service.admin.AdminCardNewsService;
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
public class AdminCardNewsController {

    private final AdminCardNewsService adminCardNewsService;

    // 카드뉴스 생성
    @PostMapping(value = "post/card-news", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO createCardNews(
            @RequestPart(value = "card_news") @Valid AdminCardNewsRequestDTO cardNews,
            @RequestPart(value = "images") List<MultipartFile> images
    ) {
        // Validation
        // MultipartFile이 모두 image인지 확인
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        for(int i = 0; i < images.size(); i++) {
            if(!images.get(i).getContentType().startsWith("image")) {
                errors.add(i + 1 + "번째 " + Responses.INVALID_IMAGE_FILE);
            }
        }

        if(!errors.isEmpty()){
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        result = adminCardNewsService.saveCardNews(cardNews, images);
        return result;
    }

    // 카드뉴스 수정
    @GetMapping("update/card-news/{id}")
    public AdminCardNewsResponseDTO getCardNews(@PathVariable Integer id) {
        AdminCardNewsResponseDTO result = adminCardNewsService.findCardNewsById(id);
        return result;
    }

    @PutMapping(value = "update/card-news/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO updateCardNews(
            @PathVariable Integer id,
            @RequestPart(value = "card_news") @Valid AdminCardNewsRequestDTO cardNews,
            @RequestPart(value = "images") List<MultipartFile> images
    ) {
        // Validation
        // MultipartFile이 모두 image인지 확인
        ResponseDTO result = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        for(int i = 0; i < images.size(); i++) {
            if(!images.get(i).getContentType().startsWith("image")) {
                errors.add(i + 1 + "번째 " + Responses.INVALID_IMAGE_FILE);
            }
        }

        if(!errors.isEmpty()){
            result.setResult(Responses.Result.FAIL.ordinal());
            result.setErrors(errors);
            return result;
        }

        result = adminCardNewsService.updateCardNews(id, cardNews, images);
        return result;
    }

    // 카드뉴스 삭제
    @DeleteMapping("delete/card-news/{id}")
    public ResponseDTO deleteCardNews(@PathVariable Integer id) {
        ResponseDTO result = adminCardNewsService.deleteCardNews(id);
        return result;
    }
}
