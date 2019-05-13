package pe.edu.upc.gamarra.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clothes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cloth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false, length=75)
	private String name;
	
	@Column(name="URLPhoto", nullable=true, length=500)
	private String URLPhoto;

	@Column(name="description", nullable=true, length=500)
	private String description;
	
	@NotNull(message="Debe seleccionar una talla")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sizes_id")
	private Size sizeId;

	@NotNull(message="Debe seleccionar una categoria")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categories_id")
	private Category categoryId;
	
	@OneToMany(mappedBy="clothId", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<UserCloth> userCloth;
	
	@OneToMany(mappedBy="clothId", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<ShopCloth> shopCloth;
	
	public Cloth () {
		userCloth = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getURLPhoto() {
		return URLPhoto;
	}

	public void setURLPhoto(String uRLPhoto) {
		URLPhoto = uRLPhoto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Size getSizeId() {
		return sizeId;
	}

	public void setSizeId(Size sizeId) {
		this.sizeId = sizeId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public List<UserCloth> getUserCloth() {
		return userCloth;
	}

	public void setUserCloth(List<UserCloth> userCloth) {
		this.userCloth = userCloth;
	}

	public List<ShopCloth> getShopCloth() {
		return shopCloth;
	}

	public void setShopCloth(List<ShopCloth> shopCloth) {
		this.shopCloth = shopCloth;
	}
	
}
