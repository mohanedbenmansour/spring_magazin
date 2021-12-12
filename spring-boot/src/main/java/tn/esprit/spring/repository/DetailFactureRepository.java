package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DetailFacture;
@Repository
public interface DetailFactureRepository extends JpaRepository<DetailFacture, Long>{

	/*
	 * Nous souhaitons calculer le revenu brut généré par un produit entre deux dates.
		Créer un service permettant de faire le calcul en respectant la signature suivante :
		
		float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate); 
		
		
		PS : le revenu brut généré correspond aux montants générées par la vente de ce
		produit ( prix * quantité pour les différentes factures)
	
	 */
}
