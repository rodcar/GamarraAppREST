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

@SuppressWarnings("serial")
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

	public ShopClothKey getId() {
		return id;
	}

	public void setId(ShopClothKey id) {
		this.id = id;
	}

	public Shop getShopId() {
		return shopId;
	}

	public void setShopId(Shop shopId) {
		this.shopId = shopId;
	}

	public Cloth getClothId() {
		return clothId;
	}

	public void setClothId(Cloth clothId) {
		this.clothId = clothId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clothId == null) ? 0 : clothId.hashCode());
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopCloth other = (ShopCloth) obj;
		if (clothId == null) {
			if (other.clothId != null)
				return false;
		} else if (!clothId.equals(other.clothId))
			return false;
		if (dateAdded == null) {
			if (other.dateAdded != null)
				return false;
		} else if (!dateAdded.equals(other.dateAdded))
			return false;
		if (editable != other.editable)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		return true;
	}
	
	
}
