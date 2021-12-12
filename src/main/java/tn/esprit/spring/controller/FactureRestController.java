package tn.esprit.spring.controller;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.service.*;

@RestController
@RequestMapping("/factures")
public class FactureRestController {

	@Autowired
	IFactureService factureService;
	
	
	@GetMapping("/get-all")
	@ResponseBody
	public List<Facture> getFactures(){
		return factureService.retrieveAllFactures();
	}
	@GetMapping("get-one/{fac-id}")
	@ResponseBody
	public Facture getFacture(@PathVariable("fac-id")Long factureId) {
		return factureService.retrieveFacture(factureId);
	}
	
	
	
	@PutMapping("/{fac-id}/assignToClient/{client-id}")
	@ResponseBody
	public void addFactureToClient(@PathVariable("fac-id")Long idFacture,@PathVariable("client-id")Long idClient) {
		 factureService.assignFactureToClient(idFacture, idClient);
	}
	
	@PutMapping("/modify")
	@ResponseBody
	public Facture updateFacture(@RequestBody Facture facture) {
		return factureService.updatFacture(facture);
	}
	
	@DeleteMapping("/remove/{fac-id}")
	@ResponseBody
	public void deleteFacture (@PathVariable("fac-id")Long id) {
		factureService.deleteFacture(id);
	}
	
	@PutMapping("/cancel/{fac-id}")
	@ResponseBody
	public void cancelFacture(@PathVariable("fac-id")Long id) {
		 factureService.cancelFacture(id);
	}
	
	
	@GetMapping("/getByClient/{id}")
	@ResponseBody
	public List<Facture> getFactureByClient(@PathVariable("id")Long idClient){
		return factureService.getFacturesByClient(idClient);
	}
	
	@PostMapping("/add/{id}")
	@ResponseBody
	public Facture addFactureAv(@RequestBody Facture facture,@PathVariable("id")Long idClient) {
		return factureService.addFacture(facture, idClient);
	}
	
	@GetMapping("/getChiffreDaffaire/{catg}/{d1}/{d2}")
	@ResponseBody
	public float getChiffreAffaireParCategorieClient
			(@PathVariable("catg")CategorieClient categorieClient,@PathVariable("d1")
			@DateTimeFormat(pattern = "yyyy-MM-dd")Date d1,
			@PathVariable("d2")@DateTimeFormat(pattern = "yyyy-MM-dd")Date d2) 
	{
		return factureService.getChiffreDaffairesParCategorie(categorieClient, d1, d2);
	}
	
	
	
}
