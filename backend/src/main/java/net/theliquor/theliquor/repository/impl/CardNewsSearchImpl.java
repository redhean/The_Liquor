package net.theliquor.theliquor.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Constants;
import net.theliquor.theliquor.domain.CardNews;
import net.theliquor.theliquor.domain.QCardNews;
import net.theliquor.theliquor.domain.QClassification;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class CardNewsSearchImpl implements CardNewsSearch {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public CardNewsSearchImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<CardNews> findCardNewsByFilters(String term, Integer page) {
        QCardNews cardNews = QCardNews.cardNews;
        BooleanBuilder builder = new BooleanBuilder();

        if(term != null && !term.isEmpty()) {
            String lowerTerm = term.toLowerCase();
            builder.and(cardNews.title.toLowerCase().contains(lowerTerm));
        }
        if(page == null) {
            page = 0;
        }

        List<CardNews> result = query
                .select(cardNews)
                .from(cardNews)
                .where(builder)
                .offset((long) page * Constants.CARD_NEWS_PAGE_SIZE)
                .limit(Constants.CARD_NEWS_PAGE_SIZE)
                .fetch();

        return result;
    }

    @Override
    public List<CardNews> findCardNewsByClassification(Integer id) {
        QCardNews cardNews = QCardNews.cardNews;

        // 하위 분류 ID를 수집하기 위한 재귀 함수
        Set<Integer> descendantIds = new HashSet<>();
        collectDescendantIds(id, descendantIds);

        if (descendantIds.isEmpty()) {
            return List.of(); // 하위 분류가 없으면 빈 리스트 반환
        }

        List<CardNews> result = query
                .selectFrom(cardNews)
                .where(cardNews.classification.id.in(descendantIds))
                .fetch();

        return result;
    }

    private void collectDescendantIds(Integer id, Set<Integer> descendantIds) {
        QClassification classification = QClassification.classification;

        List<Integer> ids = query
                .select(classification.id)
                .from(classification)
                .where(classification.parent.id.eq(id)
                        .or(classification.id.eq(id)))
                .fetch();

        for (Integer descendantId : ids) {
            if (descendantIds.add(descendantId)) {
                // 하위 주종 ID를 추가하고 재귀적으로 호출
                collectDescendantIds(descendantId, descendantIds);
            }
        }
    }
}
