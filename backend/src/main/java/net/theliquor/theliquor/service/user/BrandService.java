package net.theliquor.theliquor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Brand;
import net.theliquor.theliquor.domain.Image;
import net.theliquor.theliquor.dto.brand.BrandDTO;
import net.theliquor.theliquor.dto.brand.BrandInFilterDTO;
import net.theliquor.theliquor.repository.BrandRepository;
import net.theliquor.theliquor.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BrandService {

    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;

    /**
     * 1차 주종들을 기준으로 추천 필터 브랜드 반환
     * @param classifications
     * @return List<BrandInFilterDTO>
     */
    public List<BrandInFilterDTO> findBrandsByClassifications(List<Integer> classifications) {
        List<BrandInFilterDTO> result = new ArrayList<>();

        for(int i = 0; i < classifications.size(); i++) {
            List<Brand> temp = brandRepository.findByClassificationId(classifications.get(i));
            for (Brand brand : temp) {
                BrandInFilterDTO dto = new BrandInFilterDTO(brand.getId(), brand.getName());
                result.add(dto);
            }
        }

        return result;
    }

    /**
     * Id 값을 기준으로 브랜드 반환
     * @param id
     * @return BrandDTO -> imagePath 추가
     */
    public BrandDTO findBrandById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        BrandDTO result = new BrandDTO();

        optionalBrand.ifPresent(brand -> {
            result.setId(brand.getId());
            result.setName(brand.getName());
            result.setClassificationId(brand.getClassification().getId());
            result.setClassificationName(brand.getClassification().getName());
            result.setColor(brand.getColor());

            // ImagePath
            String imagePath = imageRepository.findImagePathByEntityTypeAndEntityId(Image.EntityType.BRAND, brand.getId());
            result.setImagePath(imagePath);
        });

        return result;
    }
}
