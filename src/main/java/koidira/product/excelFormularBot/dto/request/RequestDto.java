package koidira.product.excelFormularBot.dto.request;


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
public class RequestDto implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @NotNull
  @JsonProperty("id")
  private Long id;

  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("is_excel")
  private boolean isExcel;

  @JsonProperty("is_generate")
  private boolean isGenerate;

  @JsonProperty("request")
  private String request;
}
