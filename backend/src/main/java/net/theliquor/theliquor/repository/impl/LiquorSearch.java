package net.theliquor.theliquor.repository.impl;

import net.theliquor.theliquor.domain.Liquor;

import java.util.List;

public interface LiquorSearch {
    List<Liquor> findLiquorsByFilters(LiquorSearchCond cond);
}
