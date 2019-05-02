package pe.edu.upc.gamarra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message="Debe seleccionar una galería")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="galleries_id", nullable=false)
	private Gallery galleryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getURLPhoto() {
		return URLPhoto;
	}

	public void setURLPhoto(String uRLPhoto) {
		URLPhoto = uRLPhoto;
	}

	public Gallery getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Gallery galleryId) {
		this.galleryId = galleryId;
	}

}
