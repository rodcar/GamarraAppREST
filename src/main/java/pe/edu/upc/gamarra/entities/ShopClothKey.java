package pe.edu.upc.gamarra.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@SuppressWarnings("serial")
@Embeddable
@Data
public class ShopClothKey implements Serializable {
	
	@Column(name = "shops_id")
	Long shopId;
	
	@Column(name = "clothes_id")
	Long clothId;
}
