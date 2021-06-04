/**
 * 
 */
package ar.edu.unju.fi.tp9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.tp9.entity.Compra;

/**
 * @author Team Fernet
 *
 */
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	public List<Compra> findByProductoNombreAndTotalGreaterThanEqual(String nombreProducto, double monto);
	
	public List<Compra> findByTotalGreaterThanEqual(double monto);
	
}
