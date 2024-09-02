package net.theliquor.theliquor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.CardNews;
import net.theliquor.theliquor.domain.CardNewsImage;
import net.theliquor.theliquor.dto.guide.CardNewsDTO;
import net.theliquor.theliquor.dto.guide.CardNewsListDTO;
import net.theliquor.theliquor.dto.guide.CardNewsListItemDTO;
import net.theliquor.theliquor.repository.CardNewsImageRepository;
import net.theliquor.theliquor.repository.CardNewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GuideService {

    private final CardNewsRepository cardNewsRepository;
    private final CardNewsImageRepository cardNewsImageRepository;
    private final MakeClassificationChain makeClassificationChain;

    /**
     * 주종과 검색어를 기준으로 카드 뉴스 검색 -> 주종을 우선으로 찾는걸로
     * @param term
     * @param cardNewsClass
     * @param page
     * @return  CardNewsListDTO
     */
    public CardNewsListDTO findCardNewsByFilter(String term, Integer cardNewsClass, Integer page) {
        List<CardNews> cardNews = new ArrayList<>();

        if(page == null || page <= 0)
            page = 1;

        if(cardNewsClass != null) {
            cardNews = cardNewsRepository.findCardNewsByClassification(cardNewsClass, page);
        }
        else {
            cardNews = cardNewsRepository.findCardNewsByFilters(term, page);
        }

        CardNewsListDTO result = new CardNewsListDTO();
        result.setPage(page);
        List<CardNewsListItemDTO> cardNewsListItems = new ArrayList<>();

        for(CardNews temp : cardNews) {
            CardNewsListItemDTO itemDTO = new CardNewsListItemDTO();
            itemDTO.setId(temp.getId());
            itemDTO.setTitle(temp.getTitle());
            itemDTO.setFirstImagePath(temp.getFirstImagePath());
            itemDTO.setUpdateAt(temp.getUpdatedAt());

            cardNewsListItems.add(itemDTO);
        }

        result.setCardNewsList(cardNewsListItems);

        return result;
    }

    /**
     * 카드 뉴스 이미지들까지 함께 반환
     * @param id
     * @return CardNewsDTO
     */
    public CardNewsDTO findCardNewsById(Integer id) {
        Optional<CardNews> optionalCardNews = cardNewsRepository.findById(id);

        CardNewsDTO result = new CardNewsDTO();

        optionalCardNews.ifPresent(cardNews -> {
            // Classifications
            result.setClassifications(makeClassificationChain.formatClassificationChain(cardNews.getClassification().getId()));

            result.setTitle(cardNews.getTitle());
            result.setImageCount(cardNews.getImageCount());

            // Image Paths
            List<CardNewsImage> cardNewsImages = cardNewsImageRepository.findImagesByCardNewsId(cardNews.getId());
            List<String> imagePaths = new ArrayList<>();
            for(int i = 0; i < cardNewsImages.size(); i++) {
                imagePaths.add(cardNewsImages.get(i).getImagePath());
            }
            result.setImagePaths(imagePaths);
        });

        return result;
    }
}
