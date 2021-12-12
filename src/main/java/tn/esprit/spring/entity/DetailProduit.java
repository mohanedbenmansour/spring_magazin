package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@Table(name = "DetailProduit")
public class DetailProduit implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDetailProduit")
	private Long idDetailProduit;
	@Temporal(TemporalType.DATE)
	 Date dateCreation ;
	@Temporal(TemporalType.DATE)
	 Date dateDerniéreModification ;
	@Enumerated(EnumType.STRING)
	private CategorieProduit categorieProduit ;

	
	public DetailProduit(Date dateCreation, Date dateDerniéreModification) {
		super();
		this.dateCreation = dateCreation;
		this.dateDerniéreModification = dateDerniéreModification;
	}


	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "detailProduit")
	@JsonIgnore
	private Produit produit ;
	
}
