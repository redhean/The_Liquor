package net.theliquor.theliquor.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.domain.Classification;
import net.theliquor.theliquor.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MakeClassificationChain {

    private final ClassificationRepository classificationRepository;

    /**
     * 주종의 계층을 문자열로 만들어 반환
     * @param id -> 주류의 classification id
     * @return '>'로 구분된 classification 계층 문자열. 상위 계층부터 반환
     */
    public String formatClassificationChain(Integer id) {
        StringBuilder makeClassificationChain = new StringBuilder();
        List<Classification> classifications = classificationRepository.findClassificationChain(id);

        for (int i = 0; i < classifications.size(); i++) {
            Classification classification = classifications.get(i);
            makeClassificationChain.append(classification.getName());

            // 마지막 항목이 아니면 '>' 문자를 추가
            if (i < classifications.size() - 1) {
                makeClassificationChain.append(" > ");
            }
        }

        return makeClassificationChain.toString();
    }
}
