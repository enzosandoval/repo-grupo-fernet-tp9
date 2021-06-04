/**
 * 
 */
package ar.edu.unju.fi.tp9.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.tp9.entity.Cliente;
import ar.edu.unju.fi.tp9.repository.ClienteRepository;
import ar.edu.unju.fi.tp9.service.IClienteService;

/**
 * @author Team Fernet
 *
 */
@Service("clienteServiceImp")
public class ClienteServiceImp implements IClienteService {

	private static final Log LOGGER = LogFactory.getLog(ClienteServiceImp.class);

	@Autowired
	private ClienteRepository clienteRepository;


	@Override
	public void guardar(Cliente cliente) {
		LOGGER.info("SERVICE: ClienteService");
		LOGGER.info("METHOD: guardar()");
		clienteRepository.save(cliente);
		LOGGER.info("RESULT: Se guardó el cliente " + cliente.getNombreApellido());
	}

	@Override
	public List<Cliente> obtenerClientes() {
		LOGGER.info("SERVICE: ClienteService");
		LOGGER.info("METHOD: obtenerLista()");
		LOGGER.info("RESULT: Lista tamaño: ");
		List<Cliente> clientes = new ArrayList<>();
		clienteRepository.findAll().forEach(clientes::add);
		LOGGER.info("RESULT: Lista tamaño: " + clientes.size());
		return clientes;
	}

	@Override
	public Cliente obtenerCliente(int dni) {
		LOGGER.info("SERVICE: ClienteService");
		LOGGER.info("METHOD: buscarCliente()");
		Cliente cliente = clienteRepository.findByNroDocumento(dni);
		LOGGER.info("METHOD: Cliente: " + cliente.getNombreApellido());
		return cliente;
	}
	
	@Override
	public Cliente obtenerClienteById(long id) throws Exception{
		LOGGER.info("SERVICE: ClienteService");
		LOGGER.info("METHOD: buscarClienteById()");
		Optional<Cliente> optional = clienteRepository.findById(id);
		Cliente cliente = null;
		if (optional.isPresent()) {
			cliente = optional.get();
			LOGGER.info("RESULT: Cliente: ");
		} else {
			throw new Exception("Cliente no encontrado");
		}
		return cliente;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pagable) {
		LOGGER.info("SERVICE: ClienteService");
		LOGGER.info("METHOD: findAll()");
		LOGGER.info("RESULT: Página de clientes");
		return clienteRepository.findAll(pagable);
	}

	@Override
	public void borrar(long id) {
		LOGGER.info("SERVICE: ClienteService");
		LOGGER.info("METHOD: borrar()");
		LOGGER.info("RESULT: " + id);
		clienteRepository.deleteById(id);
	}

}
