package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import tn.esprit.spring.entity.*;

public interface IFactureService {

	public List<Facture> retrieveAllFactures();
	
	public void cancelFacture(Long id);
	
	public void enableFacture(Long id);
	
	public Facture retrieveFacture(Long id);
	
	public Facture updatFacture (Facture facture);
	
	public void deleteFacture (Long idFacture);
	
	
	
	/*
	 * advanced queries
	 */
	
	public float getChiffreDaffairesParCategorie(CategorieClient categorieClient,Date d1 , Date d2);
	
	public Facture addFacture(Facture facture, Long idClient);
	
	public void assignFactureToClient(Long  idFacture,Long idClient);
	
	public float revenuDuMagasin();
	
	public List<Facture> getFacturesByClient(Long idClient);
	
}
