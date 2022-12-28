package koidira.product.excelFormularBot.entity;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import koidira.product.excelFormularBot.listener.EntityListener;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class AbstractEntity {

  @Column(name = "deleted")
  protected Boolean deleted = false;

  @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  protected LocalDateTime deletedAt;

  @Column(name = "deleted_by")
  protected String deletedBy;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  protected LocalDateTime updatedAt;

  @Column(name = "updated_by")
  protected String updatedBy;

  @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
  protected LocalDateTime createdAt;

  @Column(name = "created_by", updatable = false)
  protected String createdBy;



  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("isDeleted", deleted)
        .append("deletedAt", deletedAt)
        .append("updatedAt", updatedAt)
        .append("createdAt", createdAt)
        .append("createdBy", createdBy)
        .append("updatedBy", updatedBy)
        .append("deletedBy", deletedBy)
        .toString();

  }
}
