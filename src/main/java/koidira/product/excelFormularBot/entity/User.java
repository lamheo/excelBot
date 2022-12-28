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
@Table(name = "user", schema = "excel_bot")
public class User extends AbstractEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private AccountRole role;

  @Column(name = "is_activated")
  private boolean isActivated = false;
 

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("name", name)
        .append("email", email)
        .append("password", password)
        .append("role", role)
        .append("isActivated", isActivated)
        .toString();
  }
}
