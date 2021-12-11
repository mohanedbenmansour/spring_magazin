package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table( name = "Client")


public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@JoinColumn(name="idClient")
private Long idClient; 
private String nom;
private String prenom;
private String email;
private String password;
@Temporal(TemporalType.DATE)
private Date dateNaissance;
@Enumerated(EnumType.STRING)
private Profession profession;
@Enumerated(EnumType.STRING)
private CategorieClient categorieClient;


@OneToMany(cascade = CascadeType.ALL,mappedBy = "client",fetch = FetchType.LAZY)

private Set<Facture> factures;



}
