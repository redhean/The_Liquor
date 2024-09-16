package net.theliquor.theliquor.dto.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemListDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;
}
