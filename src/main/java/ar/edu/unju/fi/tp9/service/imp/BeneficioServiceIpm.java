package ar.edu.unju.fi.tp9.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp9.entity.Beneficio;
import ar.edu.unju.fi.tp9.repository.BeneficioRepository;
import ar.edu.unju.fi.tp9.service.IBeneficioService;

@Service("beneficioServiceImp")
public class BeneficioServiceIpm implements IBeneficioService {

	@Autowired
	private BeneficioRepository beneficioRepository;

	@Override
	public void guardar(Beneficio beneficio) {
		beneficioRepository.save(beneficio);

	}

	@Override
	public List<Beneficio> obtenerBeneficios() {
		List<Beneficio> beneficios = beneficioRepository.findAll();
		return beneficios;
	}

	@Override
	public Beneficio buscarBeneficio(long id) {
		Beneficio beneficio= beneficioRepository.findById(id).get();
		return beneficio;
	}

}
