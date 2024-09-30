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
    @NotBlank(message = "{}")
    private String name;

    @JsonProperty("producer")
    @NotNull(message = "{}")
    private Integer producerId;

    @JsonProperty("classification")
    @NotNull(message = "{}")
    private Integer classificationId;

    @JsonProperty("color")
    @NotBlank(message = "{}")
    private String color;
}
