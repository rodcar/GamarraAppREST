package pe.edu.upc.gamarra.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstName", nullable=false, length=255)
	private String firstName;

	@Column(name="secondName", nullable=true, length=255)
	private String secondName;

	@Column(name="fathersLastName", nullable=true, length=255)
	private String fathersLastName;
	
	@Column(name="mothersLastName", nullable=true, length=255)
	private String mothersLastName;
	
	@Column(name="DNI", nullable=false, length=255)
	private String DNI;
	
	@Column(name="birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@Column(name="gender", nullable=false)
	private boolean gender;
	
	@JsonIgnore
	@Column(name="password", nullable=false, length=500)
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFathersLastName() {
		return fathersLastName;
	}

	public void setFathersLastName(String fathersLastName) {
		this.fathersLastName = fathersLastName;
	}

	public String getMothersLastName() {
		return mothersLastName;
	}

	public void setMothersLastName(String mothersLastName) {
		this.mothersLastName = mothersLastName;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
