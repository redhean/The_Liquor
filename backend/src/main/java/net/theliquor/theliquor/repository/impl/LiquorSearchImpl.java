package net.theliquor.theliquor.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Constants;
import net.theliquor.theliquor.domain.Liquor;
import net.theliquor.theliquor.domain.QClassification;
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
        List<Integer> liquorClasses = cond.getLiquorClasses();
        Float alcMin = cond.getAlcMin();
        Float alcMax = cond.getAlcMax();
        Boolean avail = cond.getAvail();
        List<Long> brands = cond.getBrands();
        Integer page = (cond.getPage() != null) ? cond.getPage() : 1;

        // 페이지가 음수거나 0이면 1로 설정
        if (page <= 0) {
            page = 1;
        }

        QLiquor liquor = QLiquor.liquor;
        BooleanBuilder builder = new BooleanBuilder();

        if (term != null && !term.isEmpty()) {
            String lowerTerm = term.toLowerCase();
            builder.and(liquor.koreanName.toLowerCase().contains(lowerTerm)
                    .or(liquor.englishName.toLowerCase().contains(lowerTerm)));
        }

        // 여러 개의 주종 및 모든 계층의 주종 처리
        if (liquorClasses != null && !liquorClasses.isEmpty()) {
            BooleanBuilder classBuilder = new BooleanBuilder();
            for (Integer liquorClass : liquorClasses) {
                // 기본 주종 조건 추가
                classBuilder.or(liquor.classification.id.eq(liquorClass));

                // 부모 주종을 계속 검사하는 조건 추가
                QClassification parentClassification = liquor.classification.parent;
                while (parentClassification != null) {
                    classBuilder.or(parentClassification.id.eq(liquorClass));
                    parentClassification = parentClassification.parent; // 계속해서 부모의 부모로 이동
                }
            }
            builder.and(classBuilder);
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

        // 여러개의 브랜드 처리
        if (brands != null && !brands.isEmpty()) {
            BooleanBuilder brandBuilder = new BooleanBuilder();
            for (Long brand : brands) {
                brandBuilder.or(liquor.brand.id.eq(brand));
            }
            builder.and(brandBuilder);
        }

        List<Liquor> result = query
                .select(liquor)
                .from(liquor)
                .where(builder)
                .offset((long) (page - 1) * Constants.LIQUOR_SEARCH_PAGE_SIZE)
                .limit(Constants.LIQUOR_SEARCH_PAGE_SIZE)
                .fetch();

        return result;
    }
}
