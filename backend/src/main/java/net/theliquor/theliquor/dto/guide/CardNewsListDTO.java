package net.theliquor.theliquor.dto.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CardNewsListDTO {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("item_list")
    private List<CardNewsListItemDTO> cardNewsList;
}
