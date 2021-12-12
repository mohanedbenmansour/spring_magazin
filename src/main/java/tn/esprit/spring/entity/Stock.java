package tn.esprit.spring.entity;
import java.io.Serializable;


import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table( name = "Stock")
public class Stock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idStock")
	private Long idStock;
	private int qte ;
	private int qteMin;
	private String libelleStock;
	
	

	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "stock",fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Produit> produits ;
	

	
	
}
