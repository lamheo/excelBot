package koidira.product.excelFormularBot.listener;


import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import koidira.product.excelFormularBot.entity.AbstractEntity;

public class EntityListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(EntityListener.class);

  @PrePersist
  private void beforeAnyCreated(Object entity) {
    LOGGER.info("[ENTITY AUDIT] beforeAnyCreated");
    if (!(entity instanceof AbstractEntity abstractEntity)) {
      LOGGER.info("Entity is not instance of MetadataEntity");
      return;
    }
    //        TODO: check auth
    //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        String userSub = null;
    //        if (authentication != null && authentication.getPrincipal() instanceof Claims claims) {
    //            userSub = claims.getSubject();
    //        }
    LocalDateTime now = LocalDateTime.now();
    if (abstractEntity.getCreatedAt() == null) {
      abstractEntity.setCreatedAt(now);
      abstractEntity.setCreatedBy("userSub");
    }
    if (abstractEntity.getUpdatedAt() == null) {
      abstractEntity.setUpdatedAt(now);
      abstractEntity.setUpdatedBy("userSub");
    }
  }

  @PreUpdate
  private void beforeAnyUpdate(Object entity) {
    LOGGER.info("[ENTITY AUDIT] beforeAnyUpdate");
    if (!(entity instanceof AbstractEntity abstractEntity)) {
      LOGGER.info("Entity is not instance of MetadataEntity");
      return;
    }
    //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        String userSub = null;
    //        if (authentication != null && authentication.getPrincipal() instanceof Claims claims) {
    //            userSub = claims.getSubject();
    //        }
    LocalDateTime now = LocalDateTime.now();
    abstractEntity.setUpdatedAt(now);
    abstractEntity.setUpdatedBy("userSub");
    if (abstractEntity.getDeleted() && abstractEntity.getDeletedAt() == null) {
      abstractEntity.setDeletedAt(now);
      abstractEntity.setDeletedBy("userSub");
    }
  }

  @PostPersist
  @PostUpdate
  private void afterAnyUpdate(Object entity) {
    LOGGER.info("[ENTITY AUDIT] add/update/delete complete for entity");
  }

  @PostLoad
  private void afterLoad(Object entity) {
    //    LOGGER.info("[ENTITY AUDIT] entity loaded from database");
  }
}
