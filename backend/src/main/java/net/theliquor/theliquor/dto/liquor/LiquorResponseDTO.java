package net.theliquor.theliquor.dto.liquor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LiquorResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("producer")
    private String producer;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("classifications")
    private String classifications;

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

    @JsonProperty("update_at")
    private LocalDateTime updateAt;

    @JsonProperty("adv")
    private Integer adv;

    @JsonProperty("image_path")
    private String imagePath;
}