package net.theliquor.theliquor.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Image;
import net.theliquor.theliquor.domain.Liquor;
import net.theliquor.theliquor.dto.liquor.LiquorResponseDTO;
import net.theliquor.theliquor.dto.liquor.LiquorListDTO;
import net.theliquor.theliquor.dto.liquor.LiquorListItemDTO;
import net.theliquor.theliquor.repository.ImageRepository;
import net.theliquor.theliquor.repository.LiquorRepository;
import net.theliquor.theliquor.repository.impl.LiquorSearchCond;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LiquorService {

    private final LiquorRepository liquorRepository;
    private final ImageRepository imageRepository;
    private final MakeClassificationChain makeClassificationChain;

    /**
     * 검색 필터 조건에 맞는 주류들을 반환 -> pagination 존재
     * @param cond -> 검색 조건 객채
     * @return -> LiquorListDTO
     */
    public LiquorListDTO findLiquorsByFilters(LiquorSearchCond cond) {
        List<Liquor> liquors = liquorRepository.findLiquorsByFilters(cond);

        int page = cond.getPage();
        LiquorListDTO result = new LiquorListDTO();
        result.setPage(page);
        List<LiquorListItemDTO> liquorListItems = new ArrayList<>();

        for (Liquor liquor : liquors) {
            LiquorListItemDTO itemDTO = new LiquorListItemDTO();
            itemDTO.setId(liquor.getId());
            itemDTO.setKoreanName(liquor.getKoreanName());
            itemDTO.setEnglishName(liquor.getEnglishName());
            itemDTO.setAlcohol(liquor.getAlcohol());

            // Classification 계층 만들기
            itemDTO.setClassifications(makeClassificationChain.formatClassificationChain(liquor.getClassification().getId()));

            // ImagePath 설정
            String imagePath = imageRepository.findImagePathByEntityTypeAndEntityId(Image.EntityType.LIQUOR, liquor.getId());
            itemDTO.setImagePath(imagePath);

            itemDTO.setBrandId(liquor.getBrand().getId());

            liquorListItems.add(itemDTO);
        }

        result.setLiquorList(liquorListItems);

        return result;
    }

    /**
     * Liquor 정보에 imagePath를 추가하여 반환
     * @param id -> Liquor id
     * @return LiquorDTO
     */
    public LiquorResponseDTO findLiquorById(Long id) {
        Optional<Liquor> optionalLiquor = liquorRepository.findById(id);

        LiquorResponseDTO result = new LiquorResponseDTO();

        optionalLiquor.ifPresent(liquor -> {
            // Liquor가 존재하는 경우 DTO에 정보 설정
            result.setId(liquor.getId());
            result.setProducer(liquor.getProducer().getName());
            result.setBrand(liquor.getBrand().getName());

            // Classification
            result.setClassifications(makeClassificationChain.formatClassificationChain(liquor.getClassification().getId()));

            result.setKoreanName(liquor.getKoreanName());
            result.setEnglishName(liquor.getEnglishName());
            result.setCountry(liquor.getCountry());
            result.setAlcohol(liquor.getAlcohol());
            result.setAged(liquor.getAged());
            result.setPrice(liquor.getPrice());
            result.setIbu(liquor.getIbu());
            result.setIsDomesticSale(liquor.getIsDomesticSale());
            result.setDescription(liquor.getDescription());
            result.setUpdateAt(liquor.getUpdatedAt());
            result.setAdv(liquor.getAdv());

            // ImagePath
            String imagePath = imageRepository.findImagePathByEntityTypeAndEntityId(Image.EntityType.LIQUOR, liquor.getId());
            result.setImagePath(imagePath);
        });

        return result;
    }
}
