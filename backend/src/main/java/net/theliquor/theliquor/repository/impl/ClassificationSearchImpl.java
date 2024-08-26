package net.theliquor.theliquor.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Classification;
import net.theliquor.theliquor.domain.QClassification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class ClassificationSearchImpl implements ClassificationSearch {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public ClassificationSearchImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Classification> findClassificationChain(Integer id) {

        QClassification classification = QClassification.classification;

        List<Classification> result = new ArrayList<>();

        Classification currentClassification = query
                .select(classification)
                .from(classification)
                .where(classification.id.eq(id))
                .fetchOne();

        while(currentClassification != null) {
            result.add(currentClassification);
            currentClassification = currentClassification.getParent();
        }

        Collections.reverse(result);

        return result;
    }
}
