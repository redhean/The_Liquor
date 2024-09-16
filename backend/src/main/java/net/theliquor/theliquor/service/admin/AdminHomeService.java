package net.theliquor.theliquor.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Constants;
import net.theliquor.theliquor.domain.Brand;
import net.theliquor.theliquor.domain.CardNews;
import net.theliquor.theliquor.domain.Liquor;
import net.theliquor.theliquor.domain.Producer;
import net.theliquor.theliquor.dto.home.AdminHomeDTO;
import net.theliquor.theliquor.dto.home.ItemListDTO;
import net.theliquor.theliquor.repository.BrandRepository;
import net.theliquor.theliquor.repository.CardNewsRepository;
import net.theliquor.theliquor.repository.LiquorRepository;
import net.theliquor.theliquor.repository.ProducerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminHomeService {

    private final LiquorRepository liquorRepository;
    private final BrandRepository brandRepository;
    private final ProducerRepository producerRepository;
    private final CardNewsRepository cardNewsRepository;


    public AdminHomeDTO findListByCategory(String type, String term, Integer page) {
        if(page == null || page < 1)
            page = 1;

        AdminHomeDTO result = new AdminHomeDTO();
        List<ItemListDTO> items = new ArrayList<>();
        Pageable pageable = PageRequest.of(page - 1, Constants.ADMIN_CATEGORY_LIST_SIZE);
        result.setPage(page);

        if(type.equals("liquor")) {
            Page<Liquor> liquors = liquorRepository.findLiquorByTerm(term, pageable);

            for(Liquor liquor : liquors.getContent()) {
                ItemListDTO item = new ItemListDTO();
                item.setId(liquor.getId());
                item.setTitle(liquor.getKoreanName().trim() + "(" + liquor.getEnglishName().trim() + ")");

                items.add(item);
            }
        }
        else if(type.equals("brand")) {
            Page<Brand> brands = brandRepository.findBrandByTerm(term, pageable);

            for(Brand brand : brands.getContent()) {
                ItemListDTO item = new ItemListDTO();
                item.setId(brand.getId());
                item.setTitle(brand.getName());

                items.add(item);
            }
        }
        else if(type.equals("producer")) {
            Page<Producer> producers = producerRepository.findProducerByTerm(term, pageable);

            for(Producer producer : producers.getContent()) {
                ItemListDTO item = new ItemListDTO();
                item.setId(Long.valueOf(producer.getId()));
                item.setTitle(producer.getName());

                items.add(item);
            }

        }
        else if(type.equals("card_news")) {
            List<CardNews> cardNews = cardNewsRepository.findCardNewsByFilters(term, page);

            for(CardNews cn : cardNews) {
                ItemListDTO item = new ItemListDTO();
                item.setId(Long.valueOf(cn.getId()));
                item.setTitle(cn.getTitle());

                items.add(item);
            }
        }

        result.setItemList(items);
        return result;
    }
}
