package net.theliquor.theliquor.controller.user;

import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.dto.LiquorDTO;
import net.theliquor.theliquor.dto.LiquorListDTO;
import net.theliquor.theliquor.repository.impl.LiquorSearchCond;
import net.theliquor.theliquor.service.LiquorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("liquor")
@RequiredArgsConstructor
public class LiquorController {

    private final LiquorService liquorService;

    @GetMapping("/search")
    public LiquorListDTO getLiquorByFilters(
            @RequestParam(value = "term", required = false) String term,
            @RequestParam(value = "class", required = false) Integer liquorClass,
            @RequestParam(value = "alc-min", required = false) Float alcMin,
            @RequestParam(value = "alc-max", required = false) Float alcMax,
            @RequestParam(value = "avail", required = false) Boolean avail,
            @RequestParam(value = "brand", required = false) Long brand,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        LiquorSearchCond liquorSearchCond = new LiquorSearchCond(term, liquorClass, alcMin, alcMax, avail, brand, page);
        LiquorListDTO result = liquorService.findLiquorsByFilters(liquorSearchCond);
        return result;
    }

    @GetMapping("/{id}")
    public LiquorDTO getLiquorById(@PathVariable Long id) {
        LiquorDTO result = liquorService.findLiquorById(id);
        return result;
    }

}
