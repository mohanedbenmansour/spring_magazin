package tn.esprit.spring.service;

import java.util.Date;

import java.util.List;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Produit;

public interface IProduitService {
	
	public List<Produit> retrieveAllProduits();
	
	public Produit addProduit(Produit p,Long idRayon , Long idStock );
	
	public void DeleteProduitById(Long idProduit);
	
	public Produit updateProduit(Produit p,Long idRayon , Long idStock);
	
	public Produit findPorduitById(Long id);
	
	
	
	
	
	public void assignProduitToStock(Long idProduit,Long idStock);
	
	public List<Produit> findProduitsByLibelle (String libelle);

	public float getRevenueBrut(Long idP , Date d1 , Date d2);
	
	public List<Produit> findProduitByCategorie(CategorieProduit categorieProduit);
}
