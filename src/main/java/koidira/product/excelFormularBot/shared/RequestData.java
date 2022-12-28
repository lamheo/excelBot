package koidira.product.excelFormularBot.shared;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestData<T> {

  @JsonProperty("data")
  private T data;

  public boolean isRequestEmpty() {
    return Objects.isNull(data);
  }
}
