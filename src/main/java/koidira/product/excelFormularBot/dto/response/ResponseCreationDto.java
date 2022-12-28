package koidira.product.excelFormularBot.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponseCreationDto implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @JsonProperty("request_id")
  private Long requestId;

  @JsonProperty("result")
  private String result;

  @JsonProperty("response")
  private String response;

  @JsonProperty("execution_time")
  private Long executionTime;

  @JsonProperty("vote")
  private Long vote;
}
