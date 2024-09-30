package net.theliquor.theliquor.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BrandRequestDTO {

    @JsonProperty("name")
    @NotBlank(message = "{BrandRequestDTO.name.blank}")
    private String name;

    @JsonProperty("producer")
    @NotNull(message = "{BrandRequestDTO.producerId.null}")
    private Integer producerId;

    @JsonProperty("classification")
    @NotNull(message = "{BrandRequestDTO.classificationId.null}")
    private Integer classificationId;

    @JsonProperty("color")
    @NotBlank(message = "{BrandRequestDTO.color.blank}")
    private String color;
}
