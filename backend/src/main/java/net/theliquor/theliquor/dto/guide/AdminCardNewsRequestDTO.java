package net.theliquor.theliquor.dto.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AdminCardNewsRequestDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("classification")
    private Integer classificationId;

    @JsonProperty("image_count")
    private Integer imageCount;
}
