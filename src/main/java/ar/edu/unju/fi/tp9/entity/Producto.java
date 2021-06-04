/**
 * 
 */
package ar.edu.unju.fi.tp9.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Team Fernet
 *
 */

@Entity
@Component
@Table(name = "PRODUCTOS")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_codigo")
	private long codigo;

	@NotEmpty(message = "Debe ingresar el nombre del producto.")
	@Column(name = "prod_nombre")
	private String nombre;

	@NotNull(message="El campo Precio no puede ser nulo")
	@Max(value=999999999,message="Los precios deben ser menores que 9 dígitos")
	@Min(value=1, message="El precio mínimo es $1")
	@Column(name = "prod_precio")
	private double precio;

	@NotEmpty(message="El campo Marca no puede ser nulo ni estar vacío")
	@Size(min=2, max=20, message="El nombre de la marca debe tener entre 2 y 20 caracteres")
	@Column(name = "prod_marca")
	private String marca;

	@NotEmpty(message="Debe agregar una descripción para el producto")
	@Size(min=4, message="Ingrese en Descripción al menos una palabra de 4 caracteres")
	@Column(name = "prod_descripcion")
	private String descripcion;

	@Lob
	@Column(name = "prod_imagen", columnDefinition = "LONGBLOB")
	private String imagen;

	@NotNull(message="Falta ingresar dato para el campo Stock")
	@Min(value=0, message="Ingrese un valor mayor o igual a 0")
	@Column(name = "prod_stock")
	private int stock;

	// Constructor por defecto
	public Producto() {
	}

	/**
	 * @param nombre
	 * @param precio
	 * @param marca
	 * @param descripcion
	 * @param imagen
	 * @param stock
	 * @param compra
	 */
	public Producto(String nombre, double precio, String marca, String descripcion, String imagen, int stock) {
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.stock = stock;
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + ", stock=" + stock + "]";
	}

}
