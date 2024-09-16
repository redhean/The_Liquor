package net.theliquor.theliquor.dto.liquor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LiquorRequestDTO {

    @JsonProperty("producer")
    private Integer producerId;

    @JsonProperty("brand")
    private Long brandId;

    @JsonProperty("classification")
    private Integer classificationId;

    @JsonProperty("korean_name")
    private String koreanName;

    @JsonProperty("english_name")
    private String englishName;

    @JsonProperty("country")
    private String country;

    @JsonProperty("alcohol")
    private Float alcohol;

    @JsonProperty("aged")
    private Integer aged;

    @JsonProperty("price")
    private String price;

    @JsonProperty("ibu")
    private Integer ibu;

    @JsonProperty("is_domestic_sale")
    private Boolean isDomesticSale;

    @JsonProperty("description")
    private String description;
}
