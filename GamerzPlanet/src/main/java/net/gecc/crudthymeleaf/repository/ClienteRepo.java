package net.gecc.crudthymeleaf.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import net.gecc.crudthymeleaf.entities.Cliente;

public interface ClienteRepo extends CrudRepository <Cliente,String>{
	
		List<Cliente> findByNombre(String nombre);
		List<Cliente> findById(Long Id);
			
}
