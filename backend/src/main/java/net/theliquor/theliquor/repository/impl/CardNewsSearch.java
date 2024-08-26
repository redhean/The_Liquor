package net.theliquor.theliquor.repository.impl;

import net.theliquor.theliquor.domain.CardNews;

import java.util.List;

public interface CardNewsSearch {
    List<CardNews> findCardNewsByFilters(String term, Integer page);

    List<CardNews> findCardNewsByClassification(Integer id);
}
