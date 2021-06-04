package ar.edu.unju.fi.tp9.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.tp9.entity.Compra;
import ar.edu.unju.fi.tp9.repository.CompraRepository;
import ar.edu.unju.fi.tp9.service.ICompraService;

/**
 * @author Team Fernet
 *
 */

@Service
public class CompraServiceImp implements ICompraService {

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);

	@Autowired
	private CompraRepository compraRepository;

	@Override
	public void guardarCompra(Compra compra) {
		LOGGER.info("SERVICE: CompraService");
		LOGGER.info("METHOD: guardarCompra()");
		compraRepository.save(compra);
		LOGGER.info("RESULT: Compra agregada con éxito");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Compra> obtenerCompras() {
		LOGGER.info("SERVICE: CompraService");
		LOGGER.info("METHOD: obtenerCompras()");
		List<Compra> compras = new ArrayList<>();
		compraRepository.findAll().forEach(compras::add);
		LOGGER.info("RESULT: Lista tamaño: " + compras.size());
		return compras;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Compra> consultarCompras(String nombreProducto, double monto) {
		LOGGER.info("SERVICE: CompraService");
		LOGGER.info("METHOD: consultarCompras()");
		List<Compra> compras = new ArrayList<Compra>();
		if (!nombreProducto.isEmpty() && monto >= 0) {
			compras = compraRepository.findByProductoNombreAndTotalGreaterThanEqual(nombreProducto, monto);
		} else if (nombreProducto.isEmpty() && monto >= 0) {
			compras = compraRepository.findByTotalGreaterThanEqual(monto);
		}
		LOGGER.info("RESULT: Lista compras tamaño: " + compras.size());
		return compras;
	}

}
