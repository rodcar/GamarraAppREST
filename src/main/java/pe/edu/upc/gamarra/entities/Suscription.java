package pe.edu.upc.gamarra.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="suscriptions")
public class Suscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// TODO Se debe no mostrar la información del usuario al obtener la lista de suscripciones
	@NotNull(message="Debe seleccionar un usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable=false)
	private User userId;
	
	@Column(name="startDate", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="endDate", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="status", nullable=false)
	private boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
