package net.theliquor.theliquor.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class LiquorSearchCond {

    private String term;
    private List<Integer> liquorClasses;
    private Float alcMin;
    private Float alcMax;
    private Boolean avail;
    private List<Long> brands;
    private Integer page;

    public LiquorSearchCond() {

    }
}
