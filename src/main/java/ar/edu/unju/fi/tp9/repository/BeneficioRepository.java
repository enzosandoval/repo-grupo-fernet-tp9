package ar.edu.unju.fi.tp9.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import ar.edu.unju.fi.tp9.entity.Beneficio;

public interface BeneficioRepository extends JpaRepository<Beneficio, Long>{
	
	public List<Beneficio> findAll(); 
	

	
}
