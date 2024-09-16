package net.theliquor.theliquor.dto.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AdminCardNewsResponseDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("primary_classification")
    private Integer primaryClassificationId;

    @JsonProperty("secondary_classification")
    private Integer secondaryClassificationId;

    @JsonProperty("image_count")
    private Integer imageCount;

    @JsonProperty("images")
    private List<String> images;
}
