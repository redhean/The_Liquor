package net.theliquor.theliquor.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Constants;
import net.theliquor.theliquor.domain.Liquor;
import net.theliquor.theliquor.domain.QLiquor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class LiquorSearchImpl implements LiquorSearch{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public LiquorSearchImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Liquor> findLiquorsByFilters(LiquorSearchCond cond) {

        String term = cond.getTerm();
        Integer liquorClass = cond.getLiquorClass();
        Float alcMin = cond.getAlcMin();
        Float alcMax = cond.getAlcMax();
        Boolean avail = cond.getAvail();
        Long brand = cond.getBrand();
        Integer page = (cond.getPage() != null) ? cond.getPage() : 0;

        QLiquor liquor = QLiquor.liquor;
        BooleanBuilder builder = new BooleanBuilder();

        if (term != null && !term.isEmpty()) {
            String lowerTerm = term.toLowerCase();
            builder.and(liquor.koreanName.toLowerCase().contains(lowerTerm)
                    .or(liquor.englishName.toLowerCase().contains(lowerTerm)));
        }
        if (liquorClass != null) {
            builder.and(liquor.classification.id.eq(liquorClass));
        }
        if (alcMin != null) {
            builder.and(liquor.alcohol.goe(alcMin));
        }
        if (alcMax != null) {
            builder.and(liquor.alcohol.loe(alcMax));
        }
        if (avail != null) {
            builder.and(liquor.isDomesticSale.eq(avail));
        }
        if (brand != null) {
            builder.and(liquor.brand.id.eq(brand));
        }

        List<Liquor> result = query
                .select(liquor)
                .from(liquor)
                .where(builder)
                .offset((long) page * Constants.LIQUOR_SEARCH_PAGE_SIZE)
                .limit(Constants.LIQUOR_SEARCH_PAGE_SIZE)
                .fetch();

        return result;
    }
}
