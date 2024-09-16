package net.theliquor.theliquor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDTO {

    @JsonProperty("result")
    private int result;

    @JsonProperty("errors")
    private List<String> errors;
}
