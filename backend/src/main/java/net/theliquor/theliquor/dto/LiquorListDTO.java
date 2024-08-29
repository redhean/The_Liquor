package net.theliquor.theliquor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LiquorListDTO {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("liquor_list")
    private List<LiquorListItemDTO> liquorList;
}
