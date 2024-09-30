package net.theliquor.theliquor.dto.liquor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LiquorRequestDTO {

    @JsonProperty("producer")
    @NotNull(message = "{LiquorRequestDTO.producerId.null}")
    private Integer producerId;

    @JsonProperty("brand")
    @NotNull(message = "{LiquorRequestDTO.brandId.null}")
    private Long brandId;

    @JsonProperty("classification")
    @NotNull(message = "{LiquorRequestDTO.classificationId.null}")
    private Integer classificationId;

    @JsonProperty("korean_name")
    @NotBlank(message = "{LiquorRequestDTO.koreanName.blank}")
    private String koreanName;

    @JsonProperty("english_name")
    @NotBlank(message = "{LiquorRequestDTO.englishName.blank}")
    private String englishName;

    @JsonProperty("country")
    @NotBlank(message = "{LiquorRequestDTO.country.blank}")
    private String country;

    @JsonProperty("alcohol")
    @NotNull(message = "{LiquorRequestDTO.alcohol.null}")
    @DecimalMin(value = "0.0", message = "{LiquorRequestDTO.alcohol.decimalMin}")
    @DecimalMax(value = "100.0", message = "{LiquorRequestDTO.alcohol.decimalMax}")
    private Float alcohol;

    @JsonProperty("aged")
    @Min(value = 0, message = "{LiquorRequestDTO.aged.min}")
    private Integer aged;

    @JsonProperty("price")
    @NotBlank(message = "{LiquorRequestDTO.price.blank}")
    private String price;

    @JsonProperty("ibu")
    @Min(value = 0, message = "{LiquorRequestDTO.ibu.min}")
    private Integer ibu;

    @JsonProperty("is_domestic_sale")
    @NotNull(message = "{LiquorRequestDTO.isDomesticSale.null}")
    private Boolean isDomesticSale;

    @JsonProperty("description")
    private String description;
}
