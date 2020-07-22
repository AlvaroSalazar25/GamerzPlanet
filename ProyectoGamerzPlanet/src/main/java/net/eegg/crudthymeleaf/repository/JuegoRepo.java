package net.eegg.crudthymeleaf.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import net.eegg.crudthymeleaf.entities.Juego;

public interface JuegoRepo extends CrudRepository <Juego, Long> {
		List<Juego> findByNombre(String nombre);
		List<Juego> findByDescripcion(String descripcion);
}
