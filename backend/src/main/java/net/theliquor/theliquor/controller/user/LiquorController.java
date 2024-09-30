package net.theliquor.theliquor.controller.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.theliquor.theliquor.config.Responses;
import net.theliquor.theliquor.dto.ResponseDTO;
import net.theliquor.theliquor.dto.liquor.LiquorResponseDTO;
import net.theliquor.theliquor.dto.liquor.LiquorListDTO;
import net.theliquor.theliquor.repository.impl.LiquorSearchCond;
import net.theliquor.theliquor.service.user.LiquorService;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("liquor")
@RequiredArgsConstructor
public class LiquorController {

    private final LiquorService liquorService;
    private final MessageSource messageSource;

    @GetMapping("/search")
    public ResponseEntity<?> getLiquorsByFilters(
            @RequestParam(value = "term", required = false) String term,
            @RequestParam(value = "class", required = false) List<Integer> liquorClasses,
            @RequestParam(value = "alc-min", required = false) Float alcMin,
            @RequestParam(value = "alc-max", required = false) Float alcMax,
            @RequestParam(value = "avail", required = false) Boolean avail,
            @RequestParam(value = "brand", required = false) List<Long> brands,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        // Validate
        ResponseDTO response = new ResponseDTO();
        List<String> errors = new ArrayList<>();
        Locale locale = Locale.KOREA;

        if (page == null || page <= 0) {
            page = 1;
        }

        if (alcMin != null && (alcMin < 0 || alcMin > 100)) {
            errors.add(messageSource.getMessage("LiquorSearchCond.alcMin", new Object[]{0}, locale));
        }

        if (alcMax != null && (alcMax < 0 || alcMax > 100)) {
            errors.add(messageSource.getMessage("LiquorSearchCond.alcMax", new Object[]{0}, locale));
        }

        if(!errors.isEmpty()) {
            response.setResult(Responses.Result.FAIL.ordinal());
            response.setErrors(errors);
            return ResponseEntity.badRequest().body(response);
        }

        LiquorSearchCond liquorSearchCond = new LiquorSearchCond(term, liquorClasses, alcMin, alcMax, avail, brands, page);
        LiquorListDTO result = liquorService.findLiquorsByFilters(liquorSearchCond);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public LiquorResponseDTO getLiquorById(@PathVariable Long id) {
        LiquorResponseDTO result = liquorService.findLiquorById(id);
        return result;
    }

}
