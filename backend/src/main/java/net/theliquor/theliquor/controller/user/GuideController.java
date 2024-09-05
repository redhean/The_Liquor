package net.theliquor.theliquor.controller.user;

import lombok.RequiredArgsConstructor;
import net.theliquor.theliquor.dto.guide.CardNewsDTO;
import net.theliquor.theliquor.dto.guide.CardNewsListDTO;
import net.theliquor.theliquor.service.user.GuideService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("guide")
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @GetMapping
    public CardNewsListDTO getCardNewsByTerm(
            @RequestParam(value = "term", required = false) String term,
            @RequestParam(value = "class", required = false) Integer cardNewsClass,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        // Validate
        if(page <= 0)
            page = 1;

        CardNewsListDTO result = guideService.findCardNewsByFilter(term, cardNewsClass,page);
        return result;
    }

    @GetMapping("card-news/{id}")
    public CardNewsDTO getCardNewsById(@PathVariable Integer id) {
        CardNewsDTO result = guideService.findCardNewsById(id);
        return result;
    }
}
