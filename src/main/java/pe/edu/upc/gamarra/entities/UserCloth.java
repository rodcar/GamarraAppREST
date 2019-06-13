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
@Table(name="users_clothes")
public class UserCloth implements Serializable {

	@EmbeddedId
	UserClothKey id;
	
	@ManyToOne
	@MapsId("users_id")
	@JoinColumn(name = "users_id", nullable = false)
	private User userId;
	
	@ManyToOne
	@MapsId("clothes_id")
	@JoinColumn(name="clothes_id", nullable=false)
	private Cloth clothId;
	
	@Column(name="visible", nullable=false)
	private boolean visible = true;
	
	@Column(name="dateAdded", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	public UserClothKey getId() {
		return id;
	}

	public void setId(UserClothKey id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Cloth getClothId() {
		return clothId;
	}

	public void setClothId(Cloth clothId) {
		this.clothId = clothId;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

}
