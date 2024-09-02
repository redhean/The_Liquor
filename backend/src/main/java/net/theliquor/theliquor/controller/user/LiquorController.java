package net.theliquor.theliquor.controller.user;

import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.dto.liquor.LiquorDTO;
import net.theliquor.theliquor.dto.liquor.LiquorListDTO;
import net.theliquor.theliquor.repository.impl.LiquorSearchCond;
import net.theliquor.theliquor.service.LiquorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liquor")
@RequiredArgsConstructor
public class LiquorController {

    private final LiquorService liquorService;

    @GetMapping("/search")
    public LiquorListDTO getLiquorsByFilters(
            @RequestParam(value = "term", required = false) String term,
            @RequestParam(value = "class", required = false) List<Integer> liquorClasses,
            @RequestParam(value = "alc-min", required = false) Float alcMin,
            @RequestParam(value = "alc-max", required = false) Float alcMax,
            @RequestParam(value = "avail", required = false) Boolean avail,
            @RequestParam(value = "brand", required = false) List<Long> brands,
            @RequestParam(value = "page", required = false) Integer page
    ) {

        // Validate
        if(page <= 0)
            page = 1;

        LiquorSearchCond liquorSearchCond = new LiquorSearchCond(term, liquorClasses, alcMin, alcMax, avail, brands, page);
        LiquorListDTO result = liquorService.findLiquorsByFilters(liquorSearchCond);
        return result;
    }

    @GetMapping("/{id}")
    public LiquorDTO getLiquorById(@PathVariable Long id) {
        LiquorDTO result = liquorService.findLiquorById(id);
        return result;
    }

}
