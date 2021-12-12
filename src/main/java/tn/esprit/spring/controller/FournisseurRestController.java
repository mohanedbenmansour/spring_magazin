package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Fournisseur;
import tn.esprit.spring.service.IFournisseurService;

@RestController
@RequestMapping("/fournisseurs")
public class FournisseurRestController {
	
	@Autowired
	IFournisseurService fournisseurService;
	
	@GetMapping("/get-all")
	@ResponseBody
	public List<Fournisseur> getFournisseurs(){
		return fournisseurService.getFournisseurs();
	}
	
	@GetMapping("/get-one/{f-id}")
	@ResponseBody
	public Fournisseur getFournisseur(@PathVariable("f-id")Long idf){
		return fournisseurService.getFournisseur(idf);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurService.addFournisseur(fournisseur);
	}
	
	@DeleteMapping("/remove/{id}")
	@ResponseBody
	public void deleteFournisseur(@PathVariable("id")Long id) {
		fournisseurService.deleteFournisseur(id);
	}
	
	@PutMapping("/modify")
	@ResponseBody
	public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur) {
		return fournisseurService.updateFrournisseur(fournisseur);
	}
	
	@PutMapping("/{f-id}/assign/{p-id}")
	@ResponseBody
	public void assignFournisseurToProduit(@PathVariable("f-id")Long fournisseurId,@PathVariable("p-id")Long produitId) {
		 fournisseurService.assignFournisseurToProduit(fournisseurId, produitId);
	}
	
	
	
}
