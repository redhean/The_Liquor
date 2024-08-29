package net.theliquor.theliquor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LiquorListItemDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("korean_name")
    private String koreanName;

    @JsonProperty("english_name")
    private String englishName;

    @JsonProperty("alcohol")
    private Float alcohol;

    @JsonProperty("classifications")
    private String classifications;

    @JsonProperty("image_path")
    private String imagePath;
}
