package tn.esprit.spring.service;

import java.util.Date;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CategorieClient;
import tn.esprit.spring.entity.DetailFacture;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.repository.*;
@Service
public class FactureService implements IFactureService{

	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private DetailFactureRepository detailFactureRepository ;
	
	@Autowired
	private ProduitRepository produitRepository ;
	

	@Override
	public List<Facture> retrieveAllFactures() {
		return factureRepository.findAll();
	}

	@Override
	public void cancelFacture(Long id) {
	 Facture facture = factureRepository.getById(id);
	 facture.setActive(false);
	 factureRepository.save(facture);
	 
		
	}

	@Override
	public Facture retrieveFacture(Long id) {
		return factureRepository.findById(id).get();
	}

	

	@Override
	public Facture updatFacture(Facture facture) {
		return factureRepository.save(facture);
	}

	@Override
	public void deleteFacture(Long idFacture) {
		factureRepository.deleteById(idFacture);
	}

	@Override
	public void assignFactureToClient(Long idFacture,Long idClient) {
		Facture facture = factureRepository.findById(idFacture).get();
		facture.setClient(clientRepository.findById(idClient).get());
		factureRepository.save(facture);
		
	}
	
	
	
	@Override
	@Scheduled(cron = "* * * 1 1 *")
	public float revenuDuMagasin() {
		List<Facture> factures = factureRepository.findAll();
		float totalRevenue=0f;
		for (Facture facture : factures) {
			totalRevenue += facture.getMontantFacture() - facture.getMontantFacture()*facture.getMontantRemise();
		}
		System.out.println(totalRevenue);
		return totalRevenue;
	}

	@Override
	public List<Facture> getFacturesByClient(Long idClient) {
		return factureRepository.findByIdClient(idClient);
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	@Transactional
	public Facture addFacture(Facture facture, Long idClient) {
		
		Set<DetailFacture> detailFactures = facture.getDetailFactures();
		float prixTotaleDeFacture = 0;
		float montantRemiseFacture = 0 ;
		Produit p ;
		
		
		for (DetailFacture detailFacture : detailFactures) {
			
			int pourcentage = detailFacture.getPourcentageRemise();
			
			p= produitRepository.findById(detailFacture.getProduit().getIdProduit()).get();
			
			detailFacture.setProduit(p);
			
			float prixTotal = p.getPrixUnitaire()*detailFacture.getQte();
			
			float montant = prixTotal*(float)pourcentage/100;

		
			
			
		
			detailFacture.setPourcentageRemise(pourcentage);
			detailFacture.setPrixTotal(prixTotal);
			detailFacture.setMontantRemise(montant);
			
			prixTotaleDeFacture += prixTotal - montant ;
			montantRemiseFacture +=  montant;
			 
			 //detailFactureRepository.save(detailFacture);
		}
		facture.setMontantFacture(prixTotaleDeFacture);
		
		facture.setMontantRemise(montantRemiseFacture);
		
		facture.setClient(clientRepository.findById(idClient).get());
		
		facture.setDetailFactures(detailFactures);
		
		facture.setDateFacture(facture.getDateFacture());
		
		
		factureRepository.save(facture);
		
		for (DetailFacture detailFacture : detailFactures) {
			detailFacture.setFacture(facture);
			detailFactureRepository.save(detailFacture);
		}
		
		
		return facture;
	}

	@Override
	public float getChiffreDaffairesParCategorie(CategorieClient categorieClient, Date d1, Date d2) {
		List<Facture> listFactures = factureRepository.findByCategorieAndDate(categorieClient, d1, d2);
		float montantTotale = 0 ;
		for (Facture facture : listFactures) {
			montantTotale += facture.getMontantFacture() ;
		}
		return montantTotale;
	}

	
	
	
	
	
	
	
	
	

}
