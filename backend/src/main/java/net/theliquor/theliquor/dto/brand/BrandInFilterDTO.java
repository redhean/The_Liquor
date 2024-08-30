package net.theliquor.theliquor.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandInFilterDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
