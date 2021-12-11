package tn.esprit.spring.repository;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.*;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	List<Produit> findByLibelle(String libelle);
	
	
	@Query("select p from Produit p where p.detailProduit.categorieProduit = :catP ")
	List<Produit> getByCategorie (@Param("catP")CategorieProduit categorieProduit);
	
	
	@Query("select df from DetailFacture df where (produit.idProduit= :id) and (facture.dateFacture between :date1 and :date2)")
	List<DetailFacture> getRevenueBrut(@Param("id")Long idProd , @Param("date1")Date d1 , @Param("date2")Date d2);
	
	
	
	
}
