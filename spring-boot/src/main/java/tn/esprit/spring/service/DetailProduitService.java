package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.DetailProduit;
import tn.esprit.spring.repository.DetailProduitRepository;
@Service
public class DetailProduitService implements IDetailProduitService{

	
	@Autowired
	private DetailProduitRepository detailProduitRepository ;
	
	@Override
	public DetailProduit addDetailProduit(DetailProduit detailProduit) {
		
		
		return detailProduitRepository.save(detailProduit);
	}
	
}
