package koidira.product.excelFormularBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import koidira.product.excelFormularBot.entity.Request;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
  Optional<Request> findById(Long id);
}
