package koidira.product.excelFormularBot.shared;


import com.fasterxml.jackson.annotation.JsonProperty;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponseData<T> {

  @Builder.Default
  @JsonProperty("timestamp")
  private Long timestamp = Instant.now().getEpochSecond();

  @Builder.Default
  @JsonProperty("status")
  private ResponseDataStatus status = ResponseDataStatus.SUCCESS;

  @JsonProperty("message")
  private String message = "";

  @JsonProperty("errors")
  private List<String> errors = new ArrayList<>();

  @JsonProperty("data")
  private T data;

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("timestamp", timestamp);
    jsonObject.put("status", status);
    jsonObject.put("message", message);
    JSONArray jsonArray = new JSONArray();
    if (Objects.nonNull(errors)) {
      for (String error : errors) {
        jsonArray.put(error);
      }
    }
    jsonObject.put("errors", jsonArray);
    jsonObject.put("data", data);
    return jsonObject;
  }
}
