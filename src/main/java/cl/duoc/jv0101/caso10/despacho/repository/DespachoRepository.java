package cl.duoc.jv0101.caso10.despacho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.jv0101.caso10.despacho.model.Despacho;

public interface DespachoRepository extends JpaRepository<Despacho, Long> {
}
