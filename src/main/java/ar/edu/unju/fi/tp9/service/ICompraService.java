package ar.edu.unju.fi.tp9.service;

import java.util.List;

import ar.edu.unju.fi.tp9.entity.Compra;

/**
 * @author Team Fernet
 *
 */
public interface ICompraService {

	public void guardarCompra(Compra compra);

	public List<Compra> obtenerCompras();
	
	public List<Compra> consultarCompras(String nombreProducto, double monto);

}
