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
@Table(name = "request", schema = "excel_bot")
public class Request extends AbstractEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "is_excel")
  private boolean isExcel;

  @Column(name = "is_generate")
  private boolean isGenerate;

  @Column(name = "request")
  private String request; 

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("userId", userId)
        .append("isExcel", isExcel)
        .append("isGenerate", isGenerate)
        .append("request", request)
        .toString();
  }
}
