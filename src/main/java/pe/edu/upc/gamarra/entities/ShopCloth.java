package pe.edu.upc.gamarra.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "shops_clothes")
public class ShopCloth implements Serializable {

	@EmbeddedId
	ShopClothKey id;
	
	@ManyToOne
	@MapsId("shops_id")
	@JoinColumn(name = "shops_id", nullable = false)
	private Shop shopId;
	
	@ManyToOne
	@MapsId("clothes_id")
	@JoinColumn(name = "clothes_id", nullable = false)
	private Cloth clothId;
	
	@Column(name="dateAdded", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;
	
	@Column(name = "editable", nullable = false)
	private boolean editable;
}
