package tn.esprit.spring.service;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.*;

@Service
public class ProduitService implements IProduitService{

	

	@Autowired
	ProduitRepository produitRepository ;
	@Autowired
	StockRepository stockRepository ;
	@Autowired
	RayonRepository rayonRepository ;
	@Autowired
	DetailProduitRepository detailProduitRepository;
	@Autowired
	FactureRepository factureRepository ;
	
	
	
	@Override
	public List<Produit> retrieveAllProduits() {
		return produitRepository.findAll();
	}
	
	@Override
	public Produit findPorduitById(Long id) {
		return produitRepository.findById(id).get();
	}
	
	@Override
	public List<Produit> findProduitsByLibelle(String libelle) {
		
		return produitRepository.findByLibelle(libelle);
	}
	
	@Override
	public Produit addProduit(Produit p, Long idRayon, Long idStock ) {
		
	
		p.setRayon(rayonRepository.findById(idRayon).get());
		p.setStock(stockRepository.findById(idStock).get());
		Date date =new Date(System.currentTimeMillis());
		DetailProduit dProduit = new DetailProduit(date,date);
		dProduit.setCategorieProduit(p.getDetailProduit().getCategorieProduit());
		detailProduitRepository.save(dProduit);
		
		//this one is for testing 
		dProduit.setProduit(p);
		
		p.setDetailProduit(dProduit);
		return produitRepository.save(p);
		 
		//alaaBenFradj
	}



	@Override
	public Produit updateProduit(Produit prod, Long idRayon, Long idStock ) {
		//to retrieve the object u must get it with the findById 
		Produit p = produitRepository.findById(prod.getIdProduit()).get();
		p.setRayon(rayonRepository.findById(idRayon).get());
		p.setStock(stockRepository.findById(idStock).get());
		p.setCode(prod.getCode());
		p.setLibelle(prod.getLibelle());
		p.setPrixUnitaire(prod.getPrixUnitaire());
		//alaaBenFradj
		
		DetailProduit dProduit = detailProduitRepository.findById(p.getDetailProduit().getIdDetailProduit()).get();
		
		
		Date date =new Date(System.currentTimeMillis());
		dProduit.setDateDerniéreModification(date);
		dProduit.setCategorieProduit(prod.getDetailProduit().getCategorieProduit());
		detailProduitRepository.save(dProduit);
		
		//this one is for testing 
		dProduit.setProduit(p);
		
		p.setDetailProduit(dProduit);
		return produitRepository.save(p);
		
		

		
		
	}

	@Override
	public void DeleteProduitById(Long idProduit) {
		Produit prod = produitRepository.findById(idProduit).get();
		produitRepository.delete(prod);
		detailProduitRepository.delete(prod.getDetailProduit());
		//alaaBenFradj
		
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).get();
		produit.setStock(stockRepository.findById(idStock).get());
		produitRepository.save(produit);
		
	}

	@Override
	public float getRevenueBrut(Long idP, Date d1, Date d2) {
		
		float revenueTot=0;
		List<DetailFacture> list = produitRepository.getRevenueBrut(idP, d1, d2);
		
		for (DetailFacture detailFacture : list) {
			revenueTot += detailFacture.getQte() * produitRepository.findById(idP).get().getPrixUnitaire();
		}
		
		return revenueTot ;
	}

	@Override
	public List<Produit> findProduitByCategorie(CategorieProduit categorieProduit) {
		
		return produitRepository.getByCategorie(categorieProduit);
	}

	
	
	



	
}
