package net.theliquor.theliquor.dto.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CardNewsListItemDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("first_image_path")
    private String firstImagePath;

    @JsonProperty("update_at")
    private LocalDateTime updateAt;
}
