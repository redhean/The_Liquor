package net.theliquor.theliquor.controller.user;

import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.dto.brand.BrandDTO;
import net.theliquor.theliquor.dto.brand.BrandInFilterDTO;
import net.theliquor.theliquor.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/filter")
    public List<BrandInFilterDTO> getBrandsByClassifications(
            @RequestParam(value = "class", required = false) List<Integer> classifications
    ) {

        List<BrandInFilterDTO> result = brandService.findBrandsByClassifications(classifications);
        return result;
    }

    @GetMapping("{id}")
    public BrandDTO getBrandById(@PathVariable Long id) {
        BrandDTO result = brandService.findBrandById(id);
        return result;
    }
}
