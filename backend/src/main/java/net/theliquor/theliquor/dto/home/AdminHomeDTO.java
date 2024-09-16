package net.theliquor.theliquor.dto.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AdminHomeDTO {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("item_list")
    private List<ItemListDTO> itemList;
}
