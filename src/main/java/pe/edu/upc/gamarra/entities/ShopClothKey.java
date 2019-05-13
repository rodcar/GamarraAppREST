package pe.edu.upc.gamarra.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@SuppressWarnings("serial")
@Embeddable
public class ShopClothKey implements Serializable {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=true)
	private Long id;

	@Column(name = "shops_id")
	Long shopId;
	
	@Column(name = "clothes_id")
	Long clothId;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getClothId() {
		return clothId;
	}

	public void setClothId(Long clothId) {
		this.clothId = clothId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clothId == null) ? 0 : clothId.hashCode());
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
		ShopClothKey other = (ShopClothKey) obj;
		if (clothId == null) {
			if (other.clothId != null)
				return false;
		} else if (!clothId.equals(other.clothId))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		return true;
	}
	
}
