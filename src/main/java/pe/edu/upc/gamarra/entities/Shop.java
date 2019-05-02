package pe.edu.upc.gamarra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shops")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="address", nullable=false, length=500)
	private String address;
	
	@Column(name="directions", nullable=false, length=500)
	private String directions;
	
	@Column(name="longitude", columnDefinition= "Decimal(9,6)", nullable=false)
	private Double longitude;
	
	@Column(name="latitude", columnDefinition= "Decimal(9,6)", nullable=false)
	private Double latitude;
	
	@Column(name="URLPhoto", nullable=true, length=700)
	private String URLPhoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
