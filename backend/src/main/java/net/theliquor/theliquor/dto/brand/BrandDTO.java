package net.theliquor.theliquor.dto.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    // 1차 주종만
    @JsonProperty("classification_id")
    private Integer classificationId;

    @JsonProperty("classification_name")
    private String classificationName;

    @JsonProperty("image_path")
    private String imagePath;

    @JsonProperty("color")
    private String color;

}
