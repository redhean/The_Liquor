package net.theliquor.theliquor.controller.admin;

import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.dto.home.AdminHomeDTO;
import net.theliquor.theliquor.service.admin.AdminHomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminHomeController {

    private final AdminHomeService adminHomeService;

    @GetMapping("/search")
    public AdminHomeDTO getListByCategory(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "term") String term,
            @RequestParam(value = "page") Integer page
    ) {
        // Validation
        // TODO
        if(page == null || page < 1)
            page = 1;

        AdminHomeDTO result = adminHomeService.findListByCategory(type, term, page);
        return result;
    }
}
