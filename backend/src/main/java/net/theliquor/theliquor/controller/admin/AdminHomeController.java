package net.theliquor.theliquor.controller.admin;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.dto.home.AdminHomeDTO;
import net.theliquor.theliquor.service.admin.AdminHomeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@Validated
public class AdminHomeController {

    private final AdminHomeService adminHomeService;

    @GetMapping("/search")
    public AdminHomeDTO getListByCategory(
            @RequestParam(value = "type") @Pattern(regexp = "liquor|brand|producer|card_news",
                    message = "{AdminHomeController.type.invalid}") String type,
            @RequestParam(value = "term") String term,
            @RequestParam(value = "page") Integer page
    ) {
        // Validation
        if(page == null || page <= 0)
            page = 1;

        AdminHomeDTO result = adminHomeService.findListByCategory(type, term, page);
        return result;
    }
}
