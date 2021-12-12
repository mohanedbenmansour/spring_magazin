package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table( name = "Produit")
public class Produit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProduit")
	private Long idProduit;
	private String code;
	private String libelle;
	private Float prixUnitaire;
	
	
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "rayonId")
	private Rayon rayon;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "stockId")
	private Stock stock;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "idDetailProduit")
	private DetailProduit detailProduit ;
	
	@OneToMany( cascade = CascadeType.ALL ,mappedBy = "produit")
	@JsonIgnore
	private Set<DetailFacture> detailFactures = new HashSet<>();


	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(joinColumns = 
				@JoinColumn(name ="produitId"),
			inverseJoinColumns = 
				@JoinColumn(name = "fournisseurId"))
	private Set<Fournisseur> fournisseurs = new HashSet<>();



/*
 * 
 * 
    @JoinTable(
        name="CUST_PHONE",
        joinColumns=
            @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="PHONE_ID", referencedColumnName="ID")
    )

 * 
 */

	
	
}
