package tn.esprit.spring.service;

import java.util.List;


import tn.esprit.spring.entity.Fournisseur;

public interface IFournisseurService {

	public Fournisseur addFournisseur(Fournisseur fournisseur);
	
	public void deleteFournisseur (Long id);
	
	public List<Fournisseur> getFournisseurs();
	
	public Fournisseur getFournisseur(Long id);
	
	public Fournisseur updateFrournisseur(Fournisseur fournisseur);
	
	public void assignFournisseurToProduit(Long idFournisseur,Long idProduit);
}
