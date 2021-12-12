package tn.esprit.spring.controller;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CategorieProduit;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.service.IProduitService;

@RestController
@RequestMapping("/products")
public class ProduitRestController {

	@Autowired
	IProduitService produitService ;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-all")
	@ResponseBody
	public List<Produit> getProducts(){
		List<Produit> listproProduits = produitService.retrieveAllProduits();
		return listproProduits;
	}
	@GetMapping("/get-one/{product-id}")
	@ResponseBody
	public Produit getProduct(@PathVariable("product-id")Long idProduit) {
		return produitService.findPorduitById(idProduit);
	}
	
	
	//http://localhost:8081/SpringMVC/product/add-product/{idRayon}/{idStock}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add/{idRayon}/{idStock}")
	@ResponseBody
	public Produit addProduit(@RequestBody Produit produit,@PathVariable("idRayon") Long idRayon,@PathVariable("idStock") Long idStock){
		return  produitService.addProduit(produit, idRayon, idStock);
		
	}
	
	@PutMapping("/modify/{idRayon}/{idStock}")
	@ResponseBody
	public Produit updateProduit(@RequestBody Produit produit,@PathVariable("idRayon") Long idRayon,@PathVariable("idStock") Long idStock){
		return  produitService.updateProduit(produit, idRayon, idStock);
	}
	@DeleteMapping("/remove/{product-id}")
	@ResponseBody
	public void deleteProduct(@PathVariable("product-id")Long id) {
		produitService.DeleteProduitById(id);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/categorie/{cat}")
	@ResponseBody
	public List<Produit> getByCategorie(@PathVariable("cat")CategorieProduit categorieProduit){
		return produitService.findProduitByCategorie(categorieProduit);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/libelle/{libelle}")
	@ResponseBody
	public List<Produit> getByLibelle(@PathVariable("libelle")String libelle){
		return produitService.findProduitsByLibelle(libelle);
	}
	
	
	
	
	@PutMapping("/assignProdToStock/{idProduit}/{idStock}")
	@ResponseBody
	public void assignProduitToStock(@PathVariable("idProduit")Long idProd,@PathVariable("idStock")Long idStock) {
		produitService.assignProduitToStock(idProd, idStock);
	}
	
	
	
	@GetMapping("/revenueBrut/{id-produit}/{date-debut}/{date-fin}")
	@ResponseBody
	public float getRevenueBrut(@PathVariable("id-produit")Long id,@PathVariable("date-debut") @DateTimeFormat(pattern = "yyyy-MM-dd")Date datestart,@PathVariable("date-fin")@DateTimeFormat(pattern = "yyyy-MM-dd")Date datefin) {
		return produitService.getRevenueBrut(id, datestart, datefin);
	}
}
