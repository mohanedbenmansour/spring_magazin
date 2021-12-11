package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.*;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>{
	
	
	@Query("select f from Facture f where id_client = :idClient")
	List<Facture> findByIdClient(@Param("idClient")Long idClient);

	@Query("select f from Facture f where id_client = :id AND f.dateFacture between :date1 AND :date2")
	List<Facture> findByIdClient(@Param("id")Long idClient,@Param("date1")Date date1 ,@Param("date2")Date date2);

	
	@Query("select f from Facture f where (f.client.categorieClient = :cat) And (f.dateFacture between :date1 and :date2) ")
	List<Facture> findByCategorieAndDate (@Param("cat")CategorieClient categorieClient ,@Param("date1")Date date1 ,@Param("date2")Date date2 );
	
	@Query("select COALESCE(Max(jeton),0) from Facture")
	Long findLastFacture();
	
	@Query("select f from Facture f where jeton = :jeton")
	Facture findByJeton(@Param("jeton")Long jeton);
	

}
