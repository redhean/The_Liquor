package net.theliquor.theliquor.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LiquorSearchCond {

    private String term;
    private Integer liquorClass;
    private Float alcMin;
    private Float alcMax;
    private Boolean avail;
    private Long brand;
    private Integer page;

    public LiquorSearchCond() {

    }
}
