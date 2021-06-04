/**
 * 
 */
package ar.edu.unju.fi.tp9.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.unju.fi.tp9.entity.Cliente;

/**
 * @author Team Fernet
 *
 */
public interface IClienteService {

	public void guardar(Cliente cliente);

	public Cliente obtenerCliente(int dni);
	
	public Cliente obtenerClienteById(long id) throws Exception;
	
	public List<Cliente> obtenerClientes();

	public Page<Cliente> findAll(Pageable pagable);

	public void borrar(long id);
	
}
