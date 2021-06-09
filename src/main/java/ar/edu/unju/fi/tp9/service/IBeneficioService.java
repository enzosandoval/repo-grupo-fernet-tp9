package ar.edu.unju.fi.tp9.service;

import java.util.List;



import ar.edu.unju.fi.tp9.entity.Beneficio;

public interface IBeneficioService {
	
	public void guardar(Beneficio beneficio);
	
	public List<Beneficio> obtenerBeneficios ();
	
	public Beneficio buscarBeneficio(long id);
	
}
