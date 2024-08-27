package net.theliquor.theliquor.repository.impl;

import net.theliquor.theliquor.domain.Classification;

import java.util.List;

public interface ClassificationSearch {
    List<Classification> findClassificationChain(Integer id);
}
