package koidira.product.excelFormularBot.entity;

import java.io.Serial;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import koidira.product.excelFormularBot.shared.AccountRole;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "response", schema = "excel_bot")
public class Response extends AbstractEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "request_id")
  private Long requestId;

  @Column(name = "result")
  private String result;

  @Column(name = "response")
  private String response;
  
  @Column(name = "execution_time")
  private Long executionTime;

  @Column(name = "vote")
  private Long vote;

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("requestId", requestId)
        .append("response", response)
        .append("executionTime", executionTime)
        .append("vote", vote)
        .toString();
  }
}
