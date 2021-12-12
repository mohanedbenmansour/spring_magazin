package tn.esprit.spring.entity;
import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DetailFacture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DetailFacture implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDetailFacture")
	private long idDetailFacture ;
	private int qte ;
	private float prixTotal ;
	private int pourcentageRemise ;
	private float montantRemise ;

	
	@ManyToOne
	@JoinColumn(name = "factureId")
	@JsonIgnore
	private Facture facture;
	
	@ManyToOne
	@JoinColumn(name = "produitId")
	private Produit produit ;
	
}
