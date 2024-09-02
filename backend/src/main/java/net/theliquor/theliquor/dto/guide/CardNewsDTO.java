package net.theliquor.theliquor.dto.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CardNewsDTO {

    @JsonProperty("classifications")
    private String classifications;

    @JsonProperty("title")
    private String title;

    @JsonProperty("image_count")
    private Integer imageCount;

    @JsonProperty("image_path")
    private List<String> imagePaths;
}
