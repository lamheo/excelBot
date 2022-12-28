package koidira.product.excelFormularBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import koidira.product.excelFormularBot.entity.Response;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepo extends JpaRepository<Response, Long> {
  Optional<Response> findById(Long id);
}
