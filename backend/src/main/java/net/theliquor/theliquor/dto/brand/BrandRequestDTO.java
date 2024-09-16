package net.theliquor.theliquor.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BrandRequestDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("producer")
    private Integer producerId;

    @JsonProperty("classification")
    private Integer classificationId;

    @JsonProperty("color")
    private String color;
}
