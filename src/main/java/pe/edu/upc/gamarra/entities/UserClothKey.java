package pe.edu.upc.gamarra.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class UserClothKey implements Serializable {
	
	@Column(name = "users_id")
	Long userId;
	
	@Column(name = "clothes_id")
	Long clothId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getClothId() {
		return clothId;
	}

	public void setClothId(Long clothId) {
		this.clothId = clothId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clothId == null) ? 0 : clothId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserClothKey other = (UserClothKey) obj;
		if (clothId == null) {
			if (other.clothId != null)
				return false;
		} else if (!clothId.equals(other.clothId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
