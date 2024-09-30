package net.theliquor.theliquor.dto.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AdminCardNewsRequestDTO {

    @JsonProperty("title")
    @NotBlank(message = "{AdminCardNewsRequestDTO.title.blank}")
    private String title;

    @JsonProperty("classification")
    @NotNull(message = "{AdminCardNewsRequestDTO.classificationId.null}")
    private Integer classificationId;

    @JsonProperty("image_count")
    @NotNull(message = "{AdminCardNewsRequestDTO.imageCount.null}")
    @Min(value = 0, message = "{AdminCardNewsRequestDTO.imageCount.min}")
    private Integer imageCount;
}
