package cl.duoc.bodegas_service.repository;

import cl.duoc.bodegas_service.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}
